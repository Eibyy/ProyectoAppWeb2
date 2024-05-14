using System;
using System.Collections.Generic;

namespace gimnasioGestion.Models;

public partial class Equipo
{
    public int EquipoId { get; set; }

    public string? NombreEquipo { get; set; }

    public string? Marca { get; set; }

    public string? Modelo { get; set; }

    public virtual ICollection<ClasesEquipo> ClasesEquipos { get; set; } = new List<ClasesEquipo>();
}
