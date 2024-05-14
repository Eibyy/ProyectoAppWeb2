using System;
using System.Collections.Generic;

namespace gimnasioGestion.Models;

public partial class Clase
{
    public int ClaseId { get; set; }

    public string? NombreClase { get; set; }

    public string? Descripcion { get; set; }

    public DateOnly? Horario { get; set; }

    public virtual ICollection<ClasesEquipo> ClasesEquipos { get; set; } = new List<ClasesEquipo>();

    public virtual ICollection<ClientesClase> ClientesClases { get; set; } = new List<ClientesClase>();

    public virtual ICollection<Personal> Personals { get; set; } = new List<Personal>();
}
