using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace gimnasioElias.Model
{
    public class Clientes
    {
        public int Cliente_id { get; set; }
        public string Direccion { get; set; }
        public string Email { get; set; }
        public string Nombre_cliente { get; set; }
        public int Telefono { set; get; }
    }
}