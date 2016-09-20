import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * CSC 1800-002 Programming Assignment #1
 * Last updated by Miguel Corte-Real on 9/11/16.
 */

public class CorteReal {
    public static void main (String[] args) throws IOException {
        Scanner fileInput = new Scanner(System.in);

        HashMap<String, Person> family = new HashMap<>();

        while (fileInput.hasNextLine()) {
            String currLine = fileInput.nextLine();
            Scanner lineInput = new Scanner(currLine);
            String lineType = lineInput.next();
            switch (lineType) {
                case "E" :
                    handleEvent(lineInput.nextLine(), family);
                    break;
                case "R" :
                    System.out.println("\n" + currLine);
                    System.out.println(handleRQuery(lineInput.nextLine(), family));
                    break;
                case "X" :
                    System.out.println("\n" + currLine);
                    System.out.println(handleXQuery(lineInput.nextLine(), family));
                    break;
                case "W" :
                    System.out.println("\n" + currLine);
                    handleWQuery(lineInput.nextLine(), family).forEach(System.out::println);
                    break;
            }
        }
    }

    private static void handleEvent(String people, HashMap<String, Person> family) {
        String[] names = people.split(" ");

        Person person1 = family.get(names[1]);
        if (person1 == null) {
            person1 = new Person(names[1]);
            family.put(names[1], person1);
        }

        Person person2 = family.get(names[2]);
        if (person2 == null) {
            person2 = new Person(names[2]);
            family.put(names[2], person2);
        }

        person1.optionalAddSpouse(person2);
        person2.optionalAddSpouse(person1);

        Person person3;
        if (names.length == 4) {
            person3 = family.get(names[3]);
            if (person3 == null) {
                person3 = new Person(names[3], person1, person2);
                family.put(names[3], person3);
            }
            person1.addChild(person3);
            person2.addChild(person3);
        }
    }

    private static String handleRQuery(String query, HashMap family) {
        String[] queryItems = query.split(" ");

        Person p1 = (Person)family.get(queryItems[1]);
        Person p2 = (Person)family.get(queryItems[2]);
        if (p1 == null || p2 == null) return "unrelated";

        Set<Person> relations = new HashSet<>();

        if (p2.getSpouses().contains(p1)) return "spouse";
        if (p2.getParents().contains(p1)) return "parent";
        if (p2.getSiblings().contains(p1)) return "sibling";
        if (p2.getHalfSiblings().contains(p1)) return "half-siblings";
        if (p2.getAncestors(relations).contains(p1)) return "ancestor";
        if (p2.getCousins().contains(p1)) return "cousin";
        return "unrelated";
    }

    private static boolean handleXQuery(String query, HashMap<String, Person> family) {
        String[] queryItems = query.split(" ");

        Person p1 = family.get(queryItems[1]);
        Person p2 = family.get(queryItems[3]);
        if (p1 == null || p2 == null) return false;

        Set<Person> relations = new HashSet<>();

        switch (queryItems[2]) {
            case "spouse" :
                return (p2.getSpouses().contains(p1));
            case "parent" :
                return (p2.getParents().contains(p1));
            case "sibling" :
                return (p2.getSiblings().contains(p1));
            case "half-sibling" :
                return (p2.getHalfSiblings().contains(p1));
            case "ancestor" :
                return (p2.getAncestors(relations).contains(p1));
            case "cousin" :
                return (p2.getCousins().contains(p1));
            case "unrelated" :
                return (getUnrelated(p2, family).contains(p1));
        }
        return false;
    }

    private static List<Person> handleWQuery(String query, HashMap<String, Person> family) {
        String[] queryItems = query.split(" ");

        Person p = family.get(queryItems[2]);
        if (p == null) return new ArrayList<>();

        Set<Person> relations = new HashSet<>();

        switch (queryItems[1]) {
            case "spouse" :
                List<Person> sortedSpouses = p.getSpouses();
                Collections.sort(sortedSpouses);
                return sortedSpouses;
            case "parent" :
                List<Person> sortedParents = p.getParents();
                Collections.sort(sortedParents);
                return sortedParents;
            case "sibling" :
                relations = p.getSiblings();
                break;
            case "half-sibling" :
                relations = p.getHalfSiblings();
                break;
            case "ancestor" :
                relations = p.getAncestors(relations);
                relations.remove(p);
                break;
            case "cousin" :
                relations = p.getCousins();
                break;
            case "unrelated" :
                relations = getUnrelated(p, family);
        }
        List<Person> sortedRelations = new ArrayList<>(relations);
        Collections.sort(sortedRelations);
        return sortedRelations;
    }

    private static Set<Person> getUnrelated(Person p, HashMap<String, Person> family) {
        Set<Person> relations = new HashSet<>();
        relations = p.getAncestors(relations);
        relations.addAll(p.getSpouses());
        relations.addAll(p.getSiblings());
        relations.addAll(p.getHalfSiblings());
        relations.addAll(p.getCousins());

        Set<Person> unrelated = new HashSet<>();
        unrelated.addAll(family.values());
        unrelated.removeAll(relations);

        return unrelated;
    }

    private static class Person implements Comparable {
        String name;
        Person[] parents = new Person[2];
        List<Person> spouses;
        List<Person> children;

        private Person(String name) {
            this.name = name;
            spouses = new ArrayList<>();
            children = new ArrayList<>();
        }

        private Person(String name, Person parent1, Person parent2) {
            this.name = name;
            parents[0] = parent1;
            parents[1] = parent2;
            spouses = new ArrayList<>();
            children = new ArrayList<>();
        }

        private void optionalAddSpouse(Person spouse) {
            if (!spouses.contains(spouse)) {
                spouses.add(spouse);
            }
        }

        private void addChild(Person child) {
            children.add(child);
        }

        private Set<Person> getSiblings() {
            Set<Person> p1Children = new HashSet<>();
            Set<Person> p2Children = new HashSet<>();
            if (parents[0] != null) p1Children.addAll(parents[0].getChildren());
            if (parents[1] != null) p2Children.addAll(parents[1].getChildren());
            p1Children.retainAll(p2Children);
            p1Children.remove(this);
            return p1Children;
        }

        private Set<Person> getHalfSiblings() {
            Set<Person> p1Children = new HashSet<>();
            Set<Person> p2Children = new HashSet<>();
            Set<Person> intersection = new HashSet<>();
            Set<Person> tmp = new HashSet<>();

            if (parents[0] != null) p1Children.addAll(parents[0].getChildren());
            if (parents[1] != null) p2Children.addAll(parents[1].getChildren());

            intersection.addAll(p1Children);
            intersection.retainAll(p2Children);

            tmp.addAll(p1Children);
            tmp.addAll(p2Children);

            tmp.removeAll(intersection);
            return tmp;
        }

        private Set<Person> getAncestors(Set<Person> ancestors) {
            ancestors.add(this);
            if (parents[0] != null) {
                ancestors.addAll(parents[0].getAncestors(ancestors));
                ancestors.addAll(parents[1].getAncestors(ancestors));
            }
            return ancestors;
        }

        private Set<Person> getCousins() {
            Set<Person> closeCousins = new HashSet<>();
            Set<Person> visitedCousins = new HashSet<>();
            Set<Person> ancestors = new HashSet<>();
            Set<Person> cousins = new HashSet<>();

            this.getAncestors(ancestors).forEach(ancestor -> closeCousins.addAll(ancestor.getChildren()));

            for (Person cousin : closeCousins) {
                if (!visitedCousins.contains(cousin)) cousins.addAll(cousin.getChildren());
                visitedCousins.add(cousin);
            }

            cousins.addAll(closeCousins);
            cousins.removeAll(getParents());
            cousins.removeAll(getSiblings());
            cousins.removeAll(getHalfSiblings());
            cousins.remove(this);
            return cousins;
        }

        private String getName() {
            return name;
        }

        private List<Person> getSpouses() {
            return spouses;
        }

        private List<Person> getChildren() {
            return children;
        }

        private List<Person> getParents() {
            if (parents[0] != null) {
                return Arrays.asList(parents);
            }
            return new ArrayList<>();
        }

        @Override
        public int compareTo(Object o) {
            Person p = (Person)o;
            return name.compareTo(p.getName());
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
