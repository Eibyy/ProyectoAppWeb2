using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using proyectoElias.Data.Servicios;
using proyectoElias.Model;

namespace proyectoElias.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class EquiposController : ControllerBase
    {
        private readonly IEquiposServices _equiposServicios;

        public EquiposController(IEquiposServices equiposServicios)
        {
            _equiposServicios = equiposServicios;
        }
        [HttpGet]
        public async Task<IActionResult> GetTodosEquipos()
        {
            return Ok(await _equiposServicios.GetTodosEquipos());
        }

        [HttpGet("{equiposId}")]
        public async Task<IActionResult> GetEquiposDetalles(int equiposId)
        {
            return Ok(await _equiposServicios.GetDetalles(equiposId));
        }

        [HttpPost]
        public async Task<IActionResult> CrearEquipos(Equipos equipos)
        {
            if (equipos == null)
                return BadRequest();

            if (!ModelState.IsValid)
                return BadRequest(ModelState);

            var created = await _equiposServicios.InsertEquipos(equipos);

            return Created("created", created);
        }

        [HttpPut]
        public async Task<IActionResult> UpdateEquipos(Equipos equipos)
        {
            if (equipos == null)
                return BadRequest();

            if (!ModelState.IsValid)
                return BadRequest(ModelState);

            await _equiposServicios.UpdateEquipos(equipos);

            return NoContent();
        }

        [HttpDelete]
        public async Task<IActionResult> DeleteEquipos(int equiposId)
        {
            object value = await _equiposServicios.DeleteEquipos(new Equipos { Equipo_id = equiposId });
            return NoContent();
        }
    }
}
