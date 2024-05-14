using System;
using System.Collections.Generic;

namespace gimnasioGestion.Models;

public partial class Personal
{
    public int PersonalId { get; set; }

    public string? NombrePersonal { get; set; }

    public int? Edad { get; set; }

    public string? Direccion { get; set; }

    public int? Salario { get; set; }

    public virtual ICollection<Role> Roles { get; set; } = new List<Role>();

    public virtual ICollection<Clase> Clases { get; set; } = new List<Clase>();
}
