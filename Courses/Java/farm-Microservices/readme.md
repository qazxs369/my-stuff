Anotar aquí:
	* Supuestos que asumieron en los desarrollos.
	* Cualquier nota que crean de importancia.
	* Documentación práctica sobre el uso de los endpoints.

Yo supongo que el precio de las gallinas es de 100 y el de los huevos de 10. La granja empieza con un
presupuesto default que se encuentra en application.properties por lo que se puede cambiar, tambien
se puede iniciar con cualquier cantidad decidida en el acto. Los huevos eclosionan al dia 4, las gallinas ponen
1 huevo los dias 3 y 6 y al dia 10 se van al cielo. Los dias avanzan cada 10 segundos


-> localhost:8090/api/farms/abrirGranja
	abre granja con el presupuesto default, todo lo que viene a continuacion necesita la granja abierta

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

********************

Los microservicios de farm, chick y egg cuentan con soporte de mysql. Dentro de src/main/resources
en application.properties cuentan con lo necesario:

-> spring.datasource.url=jdbc:mysql://localhost:3306/db_springboot_cloud
	3306 es el puerto donde esta el servidor de sql y db_springboot_cloud su nombre.
	Todo lo demas que empieza con spring.datasource es bastante autoexplicativo