package com.cebem.rickandmorty.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;

import org.springframework.web.bind.annotation.RequestParam;

import com.cebem.rickandmorty.models.CharacterModel;
import com.cebem.rickandmorty.models.CharactersModel;
import com.cebem.rickandmorty.services.RickAndMortyService;
import com.cebem.rickandmorty.utils.Utils;

@RestController
@CrossOrigin(origins = "*")

public class RickController {
    @Autowired
    RickAndMortyService rickAndMortyService;

    @GetMapping("/")
    public String saluda() {

        return "Bienvenid@ a mi API de RickAndMorty.";

    }

    @GetMapping("/random")
    public String getAleatorio() {

        Random rand = new Random();

        return Math.round(rand.nextDouble(0, 10)) + "";

    }

    @GetMapping("/palindrome/{word}")
    public String palindrome(@PathVariable String word) {

        return Utils.isPalindrome(word) ? "Si es un palindromo" : "No es un palindromor";

    }

    @GetMapping("/add")
    public String add(@RequestParam String n1, @RequestParam String n2) {

        float resultado = Float.parseFloat(n1) + Float.parseFloat(n2);
        Object params[] = { n1, n2, resultado };
        return MessageFormat.format("La suma de {0} mas {1} es igual a {2}", params);
    }

    @PostMapping("/saveOnDisk")
    public String saveOnDisk(@RequestParam Map<String, String> body) {

        String name = body.get("name");
        String price = body.get("price");
        System.out.println(name + " - " + price);

        String info = name + " " + price + "\n";

        try {
            Utils.writeOnDisk("datos.txt", info);
        } catch (IOException e) {

            return "Error al intentar escribir en el fichero.";

        }

        return "Gracias por enviar el formulario. Los datos se han guardado correctamente en el servidor.";

    }

    @PostMapping("/clearDisk")
    public static void clearFile() throws IOException {
        String fileName = "datos.txt";
        try {
            PrintWriter pw = new PrintWriter(fileName);
            pw.close();// El segundo parámetro 'false' indica modo "escritura"
        } catch (IOException e) {

        }
    }

    @DeleteMapping("/removeFromDisk")
    public String removeFromDisk() {

        boolean resultado = Utils.deleteFile("datos.txt");
        return resultado ? "Borrado correcto" : "No he podido borrar";

    }

    @GetMapping("/square")
    public String square(@RequestParam String n) {
        Double num = Double.parseDouble(n);
        return num * num + "";

    }

    @GetMapping("/mostrar-archivo")
    public String mostrarArchivo(@RequestParam("n") String nombreArchivo) throws IOException {
        Path path = Paths.get(nombreArchivo);
        String contenidoArchivo = new String(Files.readAllBytes(path));
        return contenidoArchivo;
    }

    @GetMapping("/numero-mayor")
    public String mostrarNumeroMayor(@RequestParam String n1, @RequestParam String n2, @RequestParam String n3) {

        try {

            float num1 = Float.parseFloat(n1);
            float num2 = Float.parseFloat(n2);
            float num3 = Float.parseFloat(n3);
            return Utils.getHighestNumber(num1, num2, num3) + "";

        } catch (NumberFormatException e) {
            return "No es un número.";
        }

    }

    @GetMapping("/capitalize/{text}")

    public static String capitalize(@PathVariable String text) {

        return Utils.capitalizeText(text);

    }

    @GetMapping("/random-color")
    public static String getRandomColor() {

        final String[] COLORS = { "negro", "azul", "marron", "gris", "verde", "naranja", "rojo", "blanco", "amarillo" };

        final int COLOR_COUNT = 3;
        if (COLOR_COUNT > COLORS.length)
            throw new RuntimeException("Limite de colores superado.");
        ArrayList<String> threeColors = new ArrayList<String>(Arrays.asList(COLORS));

        String finalColors = "";
        for (int i = 0; i < COLOR_COUNT; i++) {
            int random1 = Utils.getRandomValue(threeColors.size());
            finalColors += threeColors.remove(random1) + " ";

        }

        return finalColors;
    }



    @GetMapping("/rickandmorty/random")
    public String randomCharacter() {
        CharacterModel chm = rickAndMortyService.getCharacterRandom();
        return chm.name + "<br/>" + "<img width='200' src='" + chm.image + "'/>";
    }
   

    @GetMapping("/rickandmorty/all")
    public String allCharacters() {
        CharactersModel chm = rickAndMortyService.getAllCharacters();
        String enseñar = "";
        for (int i = 1; i < chm.results.size(); i++) {
            enseñar += chm.results.get(i).name + "<br/>" + "<img width='200' src='" + chm.results.get(i).image + "'/>" + "<br/>";
        }
        return enseñar;
    }
    // @GetMapping("rickandmorty/all/?page={number}")
    // public String pageAllCharacters(@PathVariable String number){




    // }


}
