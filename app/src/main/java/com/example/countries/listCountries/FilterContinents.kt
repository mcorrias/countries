package com.example.countries.listCountries

data class Continent(val name: String, val code: String){
    override fun toString(): String {
        return name
    }
}

val filterContinents = arrayOf(
    Continent(name = "Select by continent", code = ""),
    Continent(name = "All", code = ""),
    Continent(name = "Europe", code = "EU"),
    Continent(name = "Africa", code = "AF"),
    Continent(name = "Asia", code = "AS"),
    Continent(name = "North America", code = "NA"),
    Continent(name = "South America", code = "SA"),
    Continent(name = "Oceania", code = "OC"),
    Continent(name = "Antarctica" ,code = "AN")
)