/**
 * 
 */
 
/*document.getElementById("btnDelete").addEventListener("click", function() {

	id = document.getElementById("btnDelete").dataset.id;

	Swal.fire({
		title: '¿Esta seguro eliminar?',
		text: 'Continuar',
		icon: 'question',
		confirmButtonText: 'Aceptar',
		showCancelButton: true,
		cancelButtonText: "Cancelar"
	}).then((result) => {
		if (result.isConfirmed) {
			/*Swal.fire('Eliminado', '', 'success')
			window.location = "http://localhost:8080/eliminar/" + id;
		}
	})
}, false)*/


function delet(id) {
	Swal.fire({
		title: '¿Esta seguro eliminar?',
		text: 'Continuar',
		icon: 'question',
		confirmButtonText: 'Aceptar',
		showCancelButton: true,
		cancelButtonText: "Cancelar"
	}).then((result) => {
		if (result.isConfirmed) {
			/*Swal.fire('Eliminado', '', 'success')*/
			window.location = "http://localhost:8080/eliminar/" + id;
		}
	})

}

