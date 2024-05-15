using Dapper;
using MySql.Data.MySqlClient;
using MySqlX.XDevAPI;
using proyectoElias.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static Mysqlx.Crud.Order.Types;

namespace proyectoElias.Data.Servicios
{
    public class ClientesServicios : IClientesServicios
    {
        private readonly MySQLConfiguracion _connectionString;

        public ClientesServicios(MySQLConfiguracion connectionString)
        {
            _connectionString = connectionString;
        }

        //cadena de conexión
        protected MySqlConnection dbConnection()
        {
            return new MySqlConnection(_connectionString.ConnectionString);
        }



        public async Task<bool> DeleteClientes(Clientes clientes)
        {
            var db = dbConnection();
            
            var sql = @"DELETE FROM cars WHERE cliente_id = @Cliente_id";

            var result = await db.ExecuteAsync(sql, new {Cliente_id = clientes.Cliente_id});

            return result > 0;
        }

        public Task<Clientes> GetDetalles(int clienteId)
        {
            var db = dbConnection();

            var sql = @"SELECT cliente_id, direccion, email, nombre_cliente, telefono
                        FROM clientes
                        WHERE cliente_id = @Cliente_id";

            return db.QueryFirstOrDefaultAsync<Clientes>(sql, new { Cliente_id = clienteId });
        }

        public Task<IEnumerable<Clientes>> GetTodosClientes()
        {
            var db = dbConnection();

            var sql = @"SELECT cliente_id, direccion, email, nombre_cliente, telefono
                        FROM clientes";

            return db.QueryAsync<Clientes>(sql, new { });
        }
        

        public async Task<bool> InsertClientes(Clientes clientes)
        {
            var db = dbConnection();

            var sql = @"INSERT INTO clientes(cliente_id, direccion, email, nombre_cliente, telefono)
                        VALUES(@Cliente_id, @Direccion, @Email, @Nombre_cliente, @Telefono) ";

            var resut = await db.ExecuteAsync(sql, new
            { clientes.Cliente_id, clientes.Direccion, clientes.Email, clientes.Nombre_cliente, clientes.Telefono });
            return resut >0;
        }

        public async Task<bool> UpdateClientes(Clientes clientes)
        {
            var db = dbConnection();

            var sql = @"UPDATE clientes
                        SET direccion = @Direccion,
                            email = @Email,
                            nombre_cliente = @Nombre_cliente
                            telefono = @Telefono
                            WHERE cliente_id = @Cliente_id";

            var resut = await db.ExecuteAsync(sql, new
            { clientes.Cliente_id, clientes.Direccion, clientes.Email, clientes.Nombre_cliente, clientes.Telefono });
            
            return resut > 0;
        }
    }
}
