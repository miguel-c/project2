import java.util.*;
import java.io.*;
import java.lang.*;

public class Doghramadjian
{
   public static ArrayList<Person> family = new ArrayList<Person>(); //master list
 
//create person class for each individual (distinct) member in the master list, family
public static class Person
{
   public static ArrayList<Person> spouses = new ArrayList<Person>();
   public static ArrayList<Person> children = new ArrayList<Person>();
   public static ArrayList<Person> ancestors = new ArrayList<Person>();
   public static Person parent1;
   public static Person parent2;
   static String name;
   
   public Person(String name1) 
   {
      name = name1;
   }
   
   public static void setParents(Person a, Person b) 
   {
      parent1 = a;
      parent2 = b;
   }
   
   public static Person getParent1()
   {
      return parent1;
   }
   
   public static Person getParent2()
   {
      return parent2;
   }
  
   public static String getname() 
   {
      return name;
   }
   
   public static void addChildren(Person child)
   {
      if(!children.contains(child))//if not already existent, add
         children.add(child);
   }
   
   public static void addSpouse(Person spouse) //if not already existent, add
   {
      if(!spouses.contains(spouse))
         spouses.add(spouse);
   }
   public static ArrayList getSpouses() 
   {
      return spouses;
   }
   
   public static ArrayList getChildren() 
   {
      return children;
   }
  
   public static void setAncestor(Person ancestor)
   {
      if(!ancestors.contains(ancestor)) //if not already existent, add
         ancestors.add(ancestor); 
   }
   
   public static ArrayList getAncestors()
   {
      return ancestors;
   }
   
   
}

public static void E(String name1, String name2, String child)
{
boolean a = false;
boolean b = false;
boolean c = false;
Person nameA = null;
Person nameB = null;
Person nameC = null;

   //look if existing names, add if not, link if yes
   for(int i =0; i < family.size(); i++) 
   {
      if(name1.equals(family.get(i).getname()))
      {
         a = true;
         nameA = family.get(i);
      }
      if(name2.equals(family.get(i).getname()))
      {
         b = true;
         nameB = family.get(i);
      }
      if(child.equals(family.get(i).getname()))
      {
         c = true;
         nameC = family.get(i);
      }
   }
         
      //if not found/don't exist, add new Person
      if(!a)
      {
         nameA = new Person(name1);
         family.add(nameA);
      }
      if(!b)
      {
         nameB = new Person (name2);
         family.add(nameB);
      }
      if(!c)
      {
         nameC = new Person (child);
         family.add(nameC);
      } 
       
      //link appropriately
      nameA.addSpouse(nameB);
      nameB.addSpouse(nameA);
      nameA.addChildren(nameC);
      nameB.addChildren(nameC);
      nameC.setParents(nameA, nameB);
      nameC.setAncestor(nameA);
      nameC.setAncestor(nameB);
}

public static void E(String name1, String name2)
{
boolean a = false;
boolean b = false;

Person nameA;
Person nameB;

   //look if existing names, add if not, link if yes
   for(int i =0; i < family.size(); i++) 
   {
      if(name1.equals(family.get(i).getname()))
      {
         a = true;
         nameA = family.get(i);
      }
      if(name2.equals(family.get(i).getname()))
      {
         b = true;
         nameB = family.get(i);
      }
   }
        
      //if don't exist, add new Person 
      if(!a)
      {
         nameA = new Person(name1);
         family.add(nameA);
      }
      if(!b)
      {
         nameB = new Person(name2);
         family.add(nameB);
      }

      //link each to the other
      //nameA.addSpouse(nameB);
      //nameB.addSpouse(nameA);
}

public static String R(String name1, String name2)
{
boolean a = false;
boolean b = false;

Person nameA = null;
Person nameB = null;

   //look if existing name, add if not, link if yes
   for(int i =0; i < family.size(); i++) 
   {
      if(name1.equals(family.get(i).getname()))
      {
         a = true;
         nameA = family.get(i);
      }
      if(name2.equals(family.get(i).getname()))
      {
         b = true;
         nameB = family.get(i);
      }
   }   
   if(!a || !b) 
      return "unrelated";

   ArrayList<Person> spouseList  = nameB.getSpouses();
   for(int i = 0; i < spouseList.size(); i++)
   {
      if(name1.equals(spouseList.get(i).getname()))
        return "spouse";
      
   }
   
   //check for parents/mutual parents = shows siblings and half-siblings
   boolean c = false;
   boolean d = false;
   ArrayList<Person> sibList1 = nameB.getParent1().getChildren();
   ArrayList<Person> sibList2 = nameB.getParent2().getChildren();
   for(int i = 0; i < family.size(); i++)
   {
      if(name1.equals(sibList1.get(i).getname())) //gathers parent1 and his/her kids
         c = true;
      if(name1.equals(sibList2.get(i).getname())) //gathers parent2 and his/her kids
         d = true;
   }
   
   if(c && d) //share both parents
      return "sibling";
      
   if(c || d) //share only one parent
      return "half-sibling";
   
   if(name1.equals(nameB.getParent1()) || name1.equals(nameB.getParent2()))
      return "parent";
   
   //cousins
   //if parents are related
   for(int i = 0; i < family.size(); i++)
   {
      if(name1.equals(nameB.getParent1().getChildren().get(i)))
         return "cousin";
      if(name1.equals(nameB.getParent2().getChildren().get(i)))
         return "cousin";
   }
   
   //looks through list of ancestors
   for(int i = 0; i < family.size(); i++)
   {
      if(name1.equals(nameB.getAncestors().get(i)))
         return "ancestor";
   }   
return "";

} 
public static String X(String name1, String relation, String name2)
{
boolean a = false;
boolean b = false;
String type = relation;

Person nameA = null;
Person nameB = null;

   //look if existing names, if yes, find relation, if no, not related
   for(int i =0; i < family.size(); i++) 
   {
      if(name1.equals(family.get(i).getname()))
      {
         a = true;
         nameA = family.get(i);
      }
      if(name2.equals(family.get(i).getname()))
      {
         b = true;
         nameB = family.get(i);
      }
   }  
   
   if(!a || !b)
      return "unrelated";
      
   //similar to R method
   //figure out parent situation with booleans
   boolean c = false;
   boolean d = false;
   
   ArrayList<Person> sibList3 = nameA.getParent1().getChildren();
   ArrayList<Person> sibList4 = nameB.getParent1().getChildren();
   
   ArrayList<Person> spouseListx = nameB.getSpouses();
   for(int i = 0; i < family.size(); i++)
   {
      if(name1.equals(sibList3.get(i).getname())) //gathers parent1 and his/her kids
         c = true;
      if(name1.equals(sibList4.get(i).getname())) //gathers parent2 and his/her kids
         d = true;
   }
  
   if(type.equals("sibling"))
   {
      if(c && d)
         return "Yes";
      else
         return "No";
   }
   
   if(type.equals("half-sibling"))
   {
      if(c || d)
         return "Yes";
      else
         return "No";
   }
   
   //checks through list of its spouses
   
 
   if(type.equals("spouse"))
   {  
      for(int i = 0; i < spouseListx.size(); i++)
      {
         if(name1.equals(spouseListx.get(i).getname()))
            return "Yes";
         else
            return "No";
      }
   }
   
   //simple parent methods 
   if(type.equals("parent"))
   {
      if(name1.equals(nameB.getParent1().getname()) || (name1.equals(nameB.getParent2().getname())))
         return "Yes";
      else
         return "No";
   }
   
   //check if parents are siblings
   if(type.equals("cousin"))
   {
      for(int i = 0; i < family.size(); i++)
      {
         if(name1.equals(nameB.getParent1().getChildren().get(i)))
            return "Yes";
         else if(name1.equals(nameB.getParent2().getChildren().get(i)))
            return "Yes";
         else
            return "No";
      }
   }
   
   //checks ancestor list
   ArrayList<Person> ancestorList = nameB.getAncestors();
   if(type.equals("ancestor"))
   {
      for(int i = 0; i < family.size(); i++)
      {
         if(name1.equals(ancestorList.get(i).getname()))
            return "Yes";
         else
            return "No";
      }
   }
   
return "";
      
}

public static String W(String relation, String name1)
{
   Person nameA = null;
   boolean w = false;
   boolean x = false;
   boolean y = false;
   
   for(int i =0; i < family.size(); i++) 
   {
      if(name1.equals(family.get(i).getname()))
      {
         w = true;
         nameA = family.get(i);
         break;
      } 
   }
   if(!w)
      return "error";
      
   ArrayList<Person> siblings1 = nameA.getParent1().getChildren();
   ArrayList<Person> siblings2 = nameA.getParent2().getChildren();
   for(int i = 0; i < family.size(); i++)
   {
      if(name1.equals(siblings1.get(i).getname())) //gathers parent1 and his/her kids
         x = true;
      if(name1.equals(siblings2.get(i).getname())) //gathers parent2 and his/her kids
         y = true;
   }
   
   
      
   String listspouses = "";
   String type2 = relation;
   String[] spousesW = new String[nameA.spouses.size()];
  
   if(type2.equals("spouse"))
   {
      for(int i = 0; i < nameA.spouses.size(); i++)
      {
         spousesW[i] = nameA.spouses.get(i).name;
      }
      for (int i = 0; i < spousesW.length; i++)
      {  
         listspouses += spousesW[i] + "\n";
      }
         
      return listspouses;
   }  
   
      
   
   String parents = nameA.getParent1().getname() + nameA.getParent2().getname();
   if(type2.equals("parent"))
   {
      return parents;
   }    
   
   ArrayList<Person> siblingsW1 = nameA.getParent1().getChildren();
   ArrayList<Person> siblingsW2 = nameA.getParent2().getChildren();
   if(type2.equals("sibling"))
   {
      System.out.println(siblingsW1);
      System.out.println(siblingsW2);
   }
   
   //if(type2.equals("half-sibling")
     // return 

   String[] ancestorsW = new String[nameA.getAncestors().size()];
   if(type2.equals("ancestors"))
      System.out.println(ancestorsW);
   
return "";
   
}

public static void main(String[] args)
{
   Scanner scan = new Scanner(System.in);
   String str = scan.nextLine();

   StringTokenizer tokenize = new StringTokenizer(str, " ");
   String[] token = str.split(" "); //can look for E, R, X, W

   //execution
   if(token[0].equals("E"))
   {
      if(token[3]==null) //no child, just couple
         E(token[1], token[2]);
      else //account for child
         E(token[1], token[2], token[3]);
   }
   
   else if(token[0].equals("R"))
      R(token[1], token[2]);
      
   else if(token[0].equals("X"))
      X(token[1], token[2], token[3]);
      
   else if(token[0].equals("W"))
      W(token[1], token[2]);
   
  
}

}