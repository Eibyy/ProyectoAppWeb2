using System;
using System.Collections.Generic;

namespace gimnasioGestion.Models;

public partial class Cliente
{
    public int ClientesId { get; set; }

    public string? NombreClientes { get; set; }

    public decimal? Telefono { get; set; }

    public string? Direccion { get; set; }

    public string? Email { get; set; }

    public virtual ICollection<ClientesClase> ClientesClases { get; set; } = new List<ClientesClase>();
}
