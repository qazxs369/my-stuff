
/////////////////
///HEY LISTEN!///
/////////////////

Suponemos que el precio de las gallinas es de 100 y el de los huevos de 10

La granja empieza con un presupuesto fijo que se encuentra en application.properties de farm como farm.money=x,
tambien se puede crear una granja con un presupuesto arbitrario

Los huevos eclosionan al dia 4, las gallinas ponen 1 huevo los dias 3 y 6 y despues del dia 10 se van al cielo

Los dias avanzan cada 10 segundos



////////////////
///END POINTS///
////////////////

-> localhost:8090/api/farms/abrirGranja
	abre granja con un presupuesto fijo, todo lo que viene a continuacion necesita la granja abierta

-> localhost:8090/api/farms/abrirGranja/x
	abre granja con un presupuesto x

-> localhost:8090/api/farms/reporte
	reporte de cosas varias

-> localhost:8090/api/farms/comprarEgg/cantidad/x
	compra x eggs si le alcanza la guita

-> localhost:8090/api/farms/comprarChick/cantidad/x
	compra x chicks si le alcanza la guita

-> localhost:8090/api/farms/venderEgg/cantidad/x
	vende los primeros x eggs que tenga en la lista, si es que tiene le alcanzan los eggs

-> localhost:8090/api/farms/venderChick/cantidad/x
	vende los primeros x chicks que tenga en la lista, si es que tiene le alcanzan los chicks