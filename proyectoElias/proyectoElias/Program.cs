using MySql.Data.MySqlClient;
using proyectoElias.Data;
using proyectoElias.Data.Servicios;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// Correct the connection string key to match appsettings.json
var mySQLConfiguracion = new MySQLConfiguracion(builder.Configuration.GetConnectionString("MySqlConexion"));
builder.Services.AddSingleton(mySQLConfiguracion);

// Register the IClientesServicios
builder.Services.AddScoped<IClientesServicios, ClientesServicios>();
builder.Services.AddScoped<IEquiposServices, EquiposServicios>();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();
app.UseAuthorization();
app.MapControllers();
app.Run();
