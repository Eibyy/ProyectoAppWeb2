using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using gimnasioGestion.Models;

namespace gimnasioGestion.Controllers
{
    public class ClientesClasesController : Controller
    {
        private readonly GimnasioContext _context;

        public ClientesClasesController(GimnasioContext context)
        {
            _context = context;
        }

        // GET: ClientesClases
        public async Task<IActionResult> Index()
        {
            var gimnasioContext = _context.ClientesClases.Include(c => c.Clase).Include(c => c.Clientes);
            return View(await gimnasioContext.ToListAsync());
        }

        // GET: ClientesClases/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var clientesClase = await _context.ClientesClases
                .Include(c => c.Clase)
                .Include(c => c.Clientes)
                .FirstOrDefaultAsync(m => m.ClaseId == id);
            if (clientesClase == null)
            {
                return NotFound();
            }

            return View(clientesClase);
        }

        // GET: ClientesClases/Create
        public IActionResult Create()
        {
            ViewData["ClaseId"] = new SelectList(_context.Clases, "ClaseId", "ClaseId");
            ViewData["ClientesId"] = new SelectList(_context.Clientes, "ClientesId", "ClientesId");
            return View();
        }

        // POST: ClientesClases/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("ClaseId,ClientesId,FechaRegistro")] ClientesClase clientesClase)
        {
            if (ModelState.IsValid)
            {
                _context.Add(clientesClase);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            ViewData["ClaseId"] = new SelectList(_context.Clases, "ClaseId", "ClaseId", clientesClase.ClaseId);
            ViewData["ClientesId"] = new SelectList(_context.Clientes, "ClientesId", "ClientesId", clientesClase.ClientesId);
            return View(clientesClase);
        }

        // GET: ClientesClases/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var clientesClase = await _context.ClientesClases.FindAsync(id);
            if (clientesClase == null)
            {
                return NotFound();
            }
            ViewData["ClaseId"] = new SelectList(_context.Clases, "ClaseId", "ClaseId", clientesClase.ClaseId);
            ViewData["ClientesId"] = new SelectList(_context.Clientes, "ClientesId", "ClientesId", clientesClase.ClientesId);
            return View(clientesClase);
        }

        // POST: ClientesClases/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("ClaseId,ClientesId,FechaRegistro")] ClientesClase clientesClase)
        {
            if (id != clientesClase.ClaseId)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(clientesClase);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!ClientesClaseExists(clientesClase.ClaseId))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            ViewData["ClaseId"] = new SelectList(_context.Clases, "ClaseId", "ClaseId", clientesClase.ClaseId);
            ViewData["ClientesId"] = new SelectList(_context.Clientes, "ClientesId", "ClientesId", clientesClase.ClientesId);
            return View(clientesClase);
        }

        // GET: ClientesClases/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var clientesClase = await _context.ClientesClases
                .Include(c => c.Clase)
                .Include(c => c.Clientes)
                .FirstOrDefaultAsync(m => m.ClaseId == id);
            if (clientesClase == null)
            {
                return NotFound();
            }

            return View(clientesClase);
        }

        // POST: ClientesClases/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var clientesClase = await _context.ClientesClases.FindAsync(id);
            if (clientesClase != null)
            {
                _context.ClientesClases.Remove(clientesClase);
            }

            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool ClientesClaseExists(int id)
        {
            return _context.ClientesClases.Any(e => e.ClaseId == id);
        }
    }
}
