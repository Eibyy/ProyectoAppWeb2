using System;
using System.Collections.Generic;

namespace gimnasioGestion.Models;

public partial class Role
{
    public int RolId { get; set; }

    public int? PersonalId { get; set; }

    public string? NombreRol { get; set; }

    public string? Descripcion { get; set; }

    public virtual Personal? Personal { get; set; }
}
