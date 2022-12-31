<!-- @format -->
![](https://img.shields.io/static/v1?label=BY&message=LeonardoAlbornoz&color=purple)

# EXAMEN_03_TE-RICO_DESARROLLO_WEB

## Despliegue

Encuentra la app aqui [live link](https://boisterous-ganache-e7a00d.netlify.app/)

## API endeoints

- https://ejercicio-practico.onrender.com/registration
 - data:
 ```json
 {
  "name": "",
  "password": ""
 }
 ```
 
- https://ejercicio-practico.onrender.com/login/create
 - data:
 ```json
 {
  "name": "",
  "password": ""
 }
 ```
 
 - https://ejercicio-practico.onrender.com/login/
  - header: Authoriation: '[token]'


 - https://ejercicio-practico.onrender.com/venta/getall
  - header: Authoriation: '[token]'
  
 - https://ejercicio-practico.onrender.com/venta/add
  - header: Authoriation: '[token]'
  - data:
  ```json
  {
    "description": "",
    "quantitySold": 0,
    "unitaryPrice": 0.00,
    "folio": 0
  }
  ```
  
 - https://ejercicio-practico.onrender.com/venta/edit/:id
  - header: Authoriation: '[token]'
  - data:
  ```json
  {
    "description": "",
    "quantitySold": 0,
    "unitaryPrice": 0.00,
    "folio": 0
  }
  ```
  
- https://ejercicio-practico.onrender.com/venta/delete/:id

 ### Autor
 
 👤 **LeonardoAlbornoz**

 Platform | Badge |
 --- | --- |
 **GitHub**:   | [@Leboroz](https://github.com/leboroz)
 **Twitter**:  | [@Leboroz](https://twitter.com/leboroz)
 **LinkedIn**: | [Leonardo Albornoz](https://linkedin.com/in/leboroz)
