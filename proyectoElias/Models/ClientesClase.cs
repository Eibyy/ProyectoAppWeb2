using System;
using System.Collections.Generic;

namespace gimnasioGestion.Models;

public partial class ClientesClase
{
    public int ClaseId { get; set; }

    public int ClientesId { get; set; }

    public DateOnly? FechaRegistro { get; set; }

    public virtual Clase Clase { get; set; } = null!;

    public virtual Cliente Clientes { get; set; } = null!;
}
