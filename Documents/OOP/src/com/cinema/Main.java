package com.cinema;

import java.util.*;

public class Main {
    private static ArrayList<List> lists = new ArrayList<>();

    public static void main(String[] args) {

        List list = new List("Sam_Raimi", "Super_Hero");
        list.addMovie("Spiderman", "2002", "121 min", "$139 million", "1000 tenge");
        list.addMovie("Spiderman 2", "2004" , "127 min", "$200 million", "1000 tenge" );
        list.addMovie("Spiderman 3", "2007" , "139 min", "$258–350 million", "1000 tenge" );

        lists.add(list);

        list = new List("Christopher_Nolan", "Super_Hero");
        list.addMovie("Batman Begins", "2005 ", "121 min", "$150 million", "1000 tenge");
        list.addMovie("The Dark Knight", "2008", "152  min", "$185 million", "1000 tenge");
        list.addMovie("The Dark Knight Rises", "2012 ", "165  min", "$250–300 million", "1000 tenge");

        lists.add(list);

        LinkedList<Movie> watchLater_1 = new LinkedList<>();

        lists.get(0).addToWatchLater("Spiderman", watchLater_1);
        lists.get(0).addToWatchLater("Spiderman 2", watchLater_1);
        lists.get(1).addToWatchLater("Batman Begins", watchLater_1);

        watch(watchLater_1);
    }
    static void watch(LinkedList<Movie> watchLater){
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Movie> listIterator = watchLater.listIterator();

        if(watchLater.size() == 0) {
            System.out.println("This list have no movie");
        } else {
            System.out.println("Now playing " + listIterator.next().toString());
            printMenu();
        }

        while (!quit) {
            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 0:
                    System.out.println("List completed");
                    quit = true;
                    break;
                case 1:
                    if(!forward) {
                        if(listIterator.hasNext()){
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if(listIterator.hasNext()){
                        System.out.println("Now playing " + listIterator.next().toString());
                    }
                    else {
                        System.out.println("Its end of the list you not have more available movies ");
                        forward = false;
                    }
                    break;
                case 2:
                    if(forward){
                        if(listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Now playing " + listIterator.previous().toString());
                    } else {
                        System.out.println("We are at the first movie");
                        forward = false;
                    }
                    break;
                case 3:
                    if(forward) {
                        if (listIterator.hasPrevious()) {
                            System.out.println("Now playing "+ listIterator.previous().toString());
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the movie");
                        }
                    }else{
                        if(listIterator.hasNext()){
                            System.out.println("now playing " + listIterator.next().toString());
                            forward = true;
                        } else{
                            System.out.println("We are at the end of the list");
                        }
                    } break;
                case 4:
                    printList(watchLater);
                    break;
                case 7:
                    if(watchLater.size()>0){
                        listIterator.remove();
                        if(listIterator.hasNext()) {
                            System.out.println("Now playing" + listIterator.next().toString());
                        }
                        else {
                            if(listIterator.hasPrevious()) {
                                System.out.println("Now playing " + listIterator.previous().toString());
                            }
                        }
                    }
                case 9:
                    printMenu();
                    break;

            }
        }
    }

    private static void printMenu(){
        System.out.println("Available options \n press");
        System.out.println("0. To quit\n" + "" +
                " 1. To watch next movie\n" +
                " 2. To watch previous movie\n" +
                " 3. To rewatch current movie\n" +
                " 4. To show all movies\n" +
                " 5. To show all your movies\n" +
                " 6. To buy a movie\n" +
                " 7. To add current movie to viewed\n" +
                " 8. To find movie\n" +
                " 9. To show all available options\n"
                );
    }
    private static void printList(LinkedList<Movie> watchLater){
        Iterator<Movie> iterator = watchLater.iterator();
        System.out.println("=========================");

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("=========================");

    }
}