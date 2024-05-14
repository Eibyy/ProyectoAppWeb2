using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;
using Pomelo.EntityFrameworkCore.MySql.Scaffolding.Internal;

namespace gimnasioGestion.Models;

public partial class GimnasioContext : DbContext
{
    public GimnasioContext()
    {
    }

    public GimnasioContext(DbContextOptions<GimnasioContext> options)
        : base(options)
    {
    }

    public virtual DbSet<Clase> Clases { get; set; }

    public virtual DbSet<ClasesEquipo> ClasesEquipos { get; set; }

    public virtual DbSet<Cliente> Clientes { get; set; }

    public virtual DbSet<ClientesClase> ClientesClases { get; set; }

    public virtual DbSet<Equipo> Equipos { get; set; }

    public virtual DbSet<Personal> Personals { get; set; }

    public virtual DbSet<Role> Roles { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {

    }
//#warning To protect potentially sensitive information in your connection string, you should move it out of source code. You can avoid scaffolding the connection string by using the Name= syntax to read it from configuration - see https://go.microsoft.com/fwlink/?linkid=2131148. For more guidance on storing connection strings, see https://go.microsoft.com/fwlink/?LinkId=723263.
        //=> optionsBuilder.UseMySql("server=localhost;port=3306;database=gimnasio;uid=root;password=1234", Microsoft.EntityFrameworkCore.ServerVersion.Parse("8.0.37-mysql"));

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder
            .UseCollation("utf8mb4_0900_ai_ci")
            .HasCharSet("utf8mb4");

        modelBuilder.Entity<Clase>(entity =>
        {
            entity.HasKey(e => e.ClaseId).HasName("PRIMARY");

            entity.ToTable("clases");

            entity.Property(e => e.ClaseId)
                .ValueGeneratedNever()
                .HasColumnName("CLASE_ID");
            entity.Property(e => e.Descripcion)
                .HasMaxLength(100)
                .HasColumnName("DESCRIPCION");
            entity.Property(e => e.Horario).HasColumnName("HORARIO");
            entity.Property(e => e.NombreClase)
                .HasMaxLength(50)
                .HasColumnName("NOMBRE_CLASE");

            entity.HasMany(d => d.Personals).WithMany(p => p.Clases)
                .UsingEntity<Dictionary<string, object>>(
                    "PersonalClase",
                    r => r.HasOne<Personal>().WithMany()
                        .HasForeignKey("PersonalId")
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("FK_PERSONAL_CLASES2"),
                    l => l.HasOne<Clase>().WithMany()
                        .HasForeignKey("ClaseId")
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("FK_PERSONAL_CLASES"),
                    j =>
                    {
                        j.HasKey("ClaseId", "PersonalId")
                            .HasName("PRIMARY")
                            .HasAnnotation("MySql:IndexPrefixLength", new[] { 0, 0 });
                        j.ToTable("personal_clases");
                        j.HasIndex(new[] { "PersonalId" }, "FK_PERSONAL_CLASES2");
                        j.IndexerProperty<int>("ClaseId").HasColumnName("CLASE_ID");
                        j.IndexerProperty<int>("PersonalId").HasColumnName("PERSONAL_ID");
                    });
        });

        modelBuilder.Entity<ClasesEquipo>(entity =>
        {
            entity.HasKey(e => new { e.EquipoId, e.ClaseId })
                .HasName("PRIMARY")
                .HasAnnotation("MySql:IndexPrefixLength", new[] { 0, 0 });

            entity.ToTable("clases_equipos");

            entity.HasIndex(e => e.ClaseId, "FK_CLASES_EQUIPOS2");

            entity.Property(e => e.EquipoId).HasColumnName("EQUIPO_ID");
            entity.Property(e => e.ClaseId).HasColumnName("CLASE_ID");
            entity.Property(e => e.Cantidad).HasColumnName("CANTIDAD");

            entity.HasOne(d => d.Clase).WithMany(p => p.ClasesEquipos)
                .HasForeignKey(d => d.ClaseId)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK_CLASES_EQUIPOS2");

            entity.HasOne(d => d.Equipo).WithMany(p => p.ClasesEquipos)
                .HasForeignKey(d => d.EquipoId)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK_CLASES_EQUIPOS");
        });

        modelBuilder.Entity<Cliente>(entity =>
        {
            entity.HasKey(e => e.ClientesId).HasName("PRIMARY");

            entity.ToTable("clientes");

            entity.Property(e => e.ClientesId)
                .ValueGeneratedNever()
                .HasColumnName("CLIENTES_ID");
            entity.Property(e => e.Direccion)
                .HasMaxLength(50)
                .HasColumnName("DIRECCION");
            entity.Property(e => e.Email)
                .HasMaxLength(50)
                .HasColumnName("EMAIL");
            entity.Property(e => e.NombreClientes)
                .HasMaxLength(50)
                .HasColumnName("NOMBRE_CLIENTES");
            entity.Property(e => e.Telefono)
                .HasPrecision(10)
                .HasColumnName("TELEFONO");
        });

        modelBuilder.Entity<ClientesClase>(entity =>
        {
            entity.HasKey(e => new { e.ClaseId, e.ClientesId })
                .HasName("PRIMARY")
                .HasAnnotation("MySql:IndexPrefixLength", new[] { 0, 0 });

            entity.ToTable("clientes_clases");

            entity.HasIndex(e => e.ClientesId, "FK_CLIENTES_CLASES2");

            entity.Property(e => e.ClaseId).HasColumnName("CLASE_ID");
            entity.Property(e => e.ClientesId).HasColumnName("CLIENTES_ID");
            entity.Property(e => e.FechaRegistro).HasColumnName("FECHA_REGISTRO");

            entity.HasOne(d => d.Clase).WithMany(p => p.ClientesClases)
                .HasForeignKey(d => d.ClaseId)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK_CLIENTES_CLASES");

            entity.HasOne(d => d.Clientes).WithMany(p => p.ClientesClases)
                .HasForeignKey(d => d.ClientesId)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK_CLIENTES_CLASES2");
        });

        modelBuilder.Entity<Equipo>(entity =>
        {
            entity.HasKey(e => e.EquipoId).HasName("PRIMARY");

            entity.ToTable("equipos");

            entity.Property(e => e.EquipoId)
                .ValueGeneratedNever()
                .HasColumnName("EQUIPO_ID");
            entity.Property(e => e.Marca)
                .HasMaxLength(50)
                .HasColumnName("MARCA");
            entity.Property(e => e.Modelo)
                .HasMaxLength(50)
                .HasColumnName("MODELO");
            entity.Property(e => e.NombreEquipo)
                .HasMaxLength(100)
                .HasColumnName("NOMBRE_EQUIPO");
        });

        modelBuilder.Entity<Personal>(entity =>
        {
            entity.HasKey(e => e.PersonalId).HasName("PRIMARY");

            entity.ToTable("personal");

            entity.Property(e => e.PersonalId)
                .ValueGeneratedNever()
                .HasColumnName("PERSONAL_ID");
            entity.Property(e => e.Direccion)
                .HasMaxLength(50)
                .HasColumnName("DIRECCION");
            entity.Property(e => e.Edad).HasColumnName("EDAD");
            entity.Property(e => e.NombrePersonal)
                .HasMaxLength(50)
                .HasColumnName("NOMBRE_PERSONAL");
            entity.Property(e => e.Salario).HasColumnName("SALARIO");
        });

        modelBuilder.Entity<Role>(entity =>
        {
            entity.HasKey(e => e.RolId).HasName("PRIMARY");

            entity.ToTable("roles");

            entity.HasIndex(e => e.PersonalId, "FK_PERSONAL_ROLES");

            entity.Property(e => e.RolId)
                .ValueGeneratedNever()
                .HasColumnName("ROL_ID");
            entity.Property(e => e.Descripcion)
                .HasMaxLength(100)
                .HasColumnName("DESCRIPCION");
            entity.Property(e => e.NombreRol)
                .HasMaxLength(30)
                .HasColumnName("NOMBRE_ROL");
            entity.Property(e => e.PersonalId).HasColumnName("PERSONAL_ID");

            entity.HasOne(d => d.Personal).WithMany(p => p.Roles)
                .HasForeignKey(d => d.PersonalId)
                .HasConstraintName("FK_PERSONAL_ROLES");
        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
