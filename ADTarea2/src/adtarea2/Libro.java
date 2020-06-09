/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtarea2;

/**
 *
 * @author AntDVD
 */
public class Libro {
    
    private final String isbn;
    private final String titulo;
    private final String autor;
    private final Integer num_ejemplares;
    private final String editorial;
    private final Integer num_paginas;

    public Libro(String isbn, String titulo, String autor, Integer num_ejemplares, String editorial, Integer num_paginas) {
       
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.num_ejemplares = num_ejemplares;
        this.editorial = editorial;
        this.num_paginas = num_paginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public Integer getNum_ejemplares() {
        return num_ejemplares;
    }

    public String getEditorial() {
        return editorial;
    }

    public Integer getNum_paginas() {
        return num_paginas;
    }
    
    
    
    
}
