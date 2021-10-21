package com.senla.ads.exception;

public class NoElemenPresent extends RuntimeException {

        public NoElemenPresent(Long id) {
            super("No found element id="+id);
        }
    }


