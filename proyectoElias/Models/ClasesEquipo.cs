using System;
using System.Collections.Generic;

namespace gimnasioGestion.Models;

public partial class ClasesEquipo
{
    public int EquipoId { get; set; }

    public int ClaseId { get; set; }

    public int? Cantidad { get; set; }

    public virtual Clase Clase { get; set; } = null!;

    public virtual Equipo Equipo { get; set; } = null!;
}
