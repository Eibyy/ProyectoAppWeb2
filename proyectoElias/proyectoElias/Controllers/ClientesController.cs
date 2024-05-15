using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using proyectoElias.Data.Servicios;
using proyectoElias.Model;

namespace proyectoElias.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ClientesController : ControllerBase
    {
        private readonly IClientesServicios _clientesServicios;

        public ClientesController(IClientesServicios clientesServicios)
        {
            _clientesServicios = clientesServicios;
        }
        [HttpGet]
        public async Task<IActionResult> GetTodosClientes()
        {
            return Ok(await _clientesServicios.GetTodosClientes());
        }

        [HttpGet("{clientesId}")]
        public async Task<IActionResult> GetClientesDetalles(int clientesId)
        {
            return Ok(await _clientesServicios.GetDetalles(clientesId));
        }

        [HttpPost]
        public async Task<IActionResult> CrearClientes(Clientes clientes)
        {
            if (clientes == null)
                return BadRequest();
            
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            
            var created = await _clientesServicios.InsertClientes(clientes);

            return Created("created", created);
        }

        [HttpPut]
        public async Task<IActionResult> UpdateClientes(Clientes clientes)
        {
            if (clientes == null)
                return BadRequest();

            if (!ModelState.IsValid)
                return BadRequest(ModelState);

            await _clientesServicios.UpdateClientes(clientes);

            return NoContent();
        }

        [HttpDelete]
        public async Task<IActionResult> DeleteClientes(int clientesId)
        {
            await _clientesServicios.DeleteClientes(new Clientes { Cliente_id = clientesId });
            return NoContent();
        }
    }
}
