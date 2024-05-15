using Dapper;
using MySql.Data.MySqlClient;
using MySqlX.XDevAPI;
using proyectoElias.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace proyectoElias.Data.Servicios
{
    public class EquiposServicios : IEquiposServices
    {

        private readonly MySQLConfiguracion _connectionString;

        public EquiposServicios(MySQLConfiguracion connectionString)
        {
            _connectionString = connectionString;
        }

        //cadena de conexión
        protected MySqlConnection dbConnection()
        {
            return new MySqlConnection(_connectionString.ConnectionString);
        }


        //-------------------------------------
        public async Task<bool> DeleteEquipos(Equipos equipos)
        {
            var db = dbConnection();

            var sql = @"DELETE FROM equipos WHERE equipo_id = @Equipo_id";

            var result = await db.ExecuteAsync(sql, new { Equipo_id = equipos.Equipo_id });

            return result > 0;
        }

        public Task<Equipos> GetDetalles(int equipoId)
        {
            var db = dbConnection();

            var sql = @"SELECT equipo_id, marca, modelo, nombre_equipo
                        FROM equipos
                        WHERE equipo_id = @Equipo_id";

            return db.QueryFirstOrDefaultAsync<Equipos>(sql, new { Equipo_id = equipoId });
        }

        public Task<IEnumerable<Equipos>> GetTodosEquipos()
        {
            var db = dbConnection();

            var sql = @"SELECT equipo_id, marca, modelo, nombre_equipo
                        FROM equipos";

            return db.QueryAsync<Equipos>(sql, new { });
        }

        public async Task<bool> InsertEquipos(Equipos equipos)
        {
            var db = dbConnection();

            var sql = @"INSERT INTO equipos(equipo_id, marca, modelo, nombre_equipo)
                        VALUES(@Equipo_id, @Marca, @Modelo, @Nombre_equipo) ";

            var resut = await db.ExecuteAsync(sql, new
            { equipos.Equipo_id, equipos.Marca, equipos.Modelo, equipos.Nombre_equipo });
            return resut > 0;
        }

        public async Task<bool> UpdateEquipos(Equipos equipos)
        {
            var db = dbConnection();

            var sql = @"UPDATE equipos
                        SET marca = @Marca,
                            modelo = @Modelo,
                            nombre_equipo = @Nombre_equipo
                            WHERE cliente_id = @Equipo_id";

            var resut = await db.ExecuteAsync(sql, new
            { equipos.Equipo_id, equipos.Marca, equipos.Modelo, equipos.Nombre_equipo });

            return resut > 0;
        }
    }
}
