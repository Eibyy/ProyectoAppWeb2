using gimnasioElias.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace gimnasioElias.Data.Servicios
{
    public interface IClientesServicios
    {
        Task<IEnumerable<Clientes>> GetTodosClientes();

        Task<Clientes> GetDetalles(int clienteId);
        Task<bool> InsertClientes(Clientes clientes);
        Task<bool> UpdateClientes(Clientes clientes);
        Task<bool> DeleteClientes(Clientes clientes);
    }
}
