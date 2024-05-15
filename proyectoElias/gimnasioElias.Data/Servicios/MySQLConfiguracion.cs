using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace gimnasioElias.Data.Servicios
{
    public class MySQLConfiguracion
    {
        //metodo para realizar la conexión a través del llamado de appsettings.json para las credenciaels de la db
        public MySQLConfiguracion(string connectionString)
        {
            ConnectionString = connectionString;
        }

        public string ConnectionString { get; set; }
    
    }
}
