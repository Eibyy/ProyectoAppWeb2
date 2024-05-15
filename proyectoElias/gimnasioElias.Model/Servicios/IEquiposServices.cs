using gimnasioElias.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace gimnasioElias.Data.Servicios
{
    public interface IEquiposServices
    {
        Task<IEnumerable<Equipos>> GetTodosEquipos();

        Task<Equipos> GetDetalles(int equipoId);
        Task<bool> InsertEquipos(Equipos equipos);
        Task<bool> UpdateEquipos(Equipos equipos);
        Task<bool> DeleteEquipos(Equipos equipos);
    }
}
