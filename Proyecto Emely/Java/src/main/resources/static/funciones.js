function eliminar(id) {
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: "btn btn-success",
            cancelButton: "btn btn-danger"
        },
        buttonsStyling: false
    });
    swalWithBootstrapButtons.fire({
        title: "¿Estás seguro?",
        text: "¡No podrás revertir esto!",
        icon: "Advertencia",
        showCancelButton: true,
        confirmButtonText: "¡Sí, eliminarlo!",
        cancelButtonText: "No, cancelarlo!",
        reverseButtons: true
    }).then((result) => {
        if (result){
          $.ajax({
              url:"/eliminar/"+id,
              success: function(res){
                 console.log(res)
              }
          })
        }
        if (result.isConfirmed) {
            swalWithBootstrapButtons.fire({
                title: "¡Eliminado!",
                text: "Tu archivo ha sido eliminado.",
                icon: "correctamente"
            });

            if (result){
                location.href="/listar"
            }
        } else if (
            result.dismiss === Swal.DismissReason.cancel
        ) {
            swalWithBootstrapButtons.fire({
                title: "Cancelado",
                text: "Tu archivo está a salvo :)",
                icon: "error"
            });
        }
    });
}