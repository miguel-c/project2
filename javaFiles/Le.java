 import java.util.*;
 import java.io.*;
 
 public class Le
 {
 
  
   public static void main(String args[]) throws Exception {
   {
//Scanner
Scanner scan = new Scanner(System.in);
System.out.println("Input name of file: (Please paste all off test code here. You may also manually enter test code as well by typing");

//Hashmap creation
HashMap<String, Person> hmap = new HashMap<String, Person>();

//FileReader

while(true)
{
String file = scan.nextLine();

String[] filestrings = file.split(" ");


//for (int i = 0; i < filestrings.length; i++)
//{
//System.out.println(filestrings[i]);
//}

//If first letter equals E, do this.
if(filestrings[0] .equals("E"))
{
   Person p1;
   Person p2;
   Person p3;

   //Check if first person exists in hash table
   if(hmap.get(filestrings[1]) == null)
   {
   p1 = new Person(filestrings[1]);
   hmap.put (filestrings[1],p1); 

  }else{
   p1 = hmap.get(filestrings[1]);
   }
   
   
   //Check if second person exists in hash table
   if(hmap.get(filestrings[2]) == null)
   {
   p2 = new Person(filestrings[2]);
   hmap.put(filestrings[2],p2);
   
   
   }else{
   p2 = hmap.get(filestrings[2]);
  }
  
  
  //Setting spouses
  p1.setSpouse(p2);
  p2.setSpouse(p1);
  
  
  
   
   //Check if the third person exists
   //if(hmap.get(filestrings[3]) == null)
   //{
   p3 = new Person(filestrings[3]);
   hmap.put(filestrings[3],p3);
   //Setting Children
   p1.setChildren(p3);
   p2.setChildren(p3);
   //Set Parents
   p3.setParent1(p1);
   p3.setParent2(p2);
   // }
}

//What if the first letter equals w, print all people associated with second word. 

if(filestrings[0] .equals("W"))
{

   //if the keyword is parent
   if(filestrings[1] .equals("parent"))
   {
   ArrayList<String> allParents = new ArrayList<String>();
   allParents.add(hmap.get(filestrings[2]).getParent1().getName());
   allParents.add(hmap.get(filestrings[2]).getParent2().getName());
   Collections.sort(allParents);
   
   System.out.println(allParents);
   }
   
    //if the keyword is half halfsibling
   if(filestrings[1] .equals("halfsibling"))
   {
   ArrayList<String> allSiblings = new ArrayList<String>();
  
   
   for(Person p: hmap.get(filestrings[2]).halfSiblings())
   {
   allSiblings.add(p.getName());
   }
   
   Collections.sort(allSiblings);
   
   System.out.println(allSiblings); 

   }


     //if the keyword is sibling
   if(filestrings[1] .equals("sibling"))
   {
   
   ArrayList<String> sib = new ArrayList<String>();
   
   for (Person p: hmap.get(filestrings[2]).getSiblings())
   {
   sib.add(p.getName());
   }
   
   Collections.sort(sib);
   
   System.out.println(sib);
   
   
   }
   
     //if the keyword is cousin
   if(filestrings[1] .equals("cousin"))
   {
   
   
   }
   
     //if the keyword is ancestor
   if(filestrings[1] .equals("ancestor"))
   {
   
   ArrayList<String> AllAncestors = new ArrayList<String>();
   
   for (Person p: hmap.get(filestrings[2]).Ancestors())
   {
   AllAncestors.add(p.getName());
   }
   
   Collections.sort(AllAncestors);
   System.out.println(AllAncestors);

   
   
   }
   
     //if the keyword is spouse
   if(filestrings[1] .equals("spouse"))
   {
   
   ArrayList<String> theSpouse = new ArrayList<String>();
   
   for (Person p: hmap.get(filestrings[2]).getSpouse())
   {
   theSpouse.add(p.getName());
   }
   Collections.sort(theSpouse);
   
   System.out.println(theSpouse);
  
   
   
   }

}


//If the file says X first
if(filestrings[0] .equals("X"))
{

   if(filestrings[2].equals("sibling"))
   {
    
    ArrayList<String> XSibling = new ArrayList<String>();
      for (Person p: hmap.get(filestrings[1]).getSiblings())
      {
      XSibling.add(p.getName());
      }
      
      boolean compare = false;
    
      
    for (int i = 0; i < XSibling.size(); i++)
    {
    
   if(filestrings[3].equals(XSibling.get(i)))
         {
            compare = true;
         }
         
         }
         
   if(compare == true)
   {
   System.out.println("Yes");
   }
   
   if(compare == false)
   
   {
   System.out.println("No");
   }     

    
  }
   
   
   
     if(filestrings[2].equals("parent"))
   {
   
      if(hmap.get(filestrings[3]).getParent1().getName().equals(filestrings[1]))
      {
      System.out.println("Yes");
      }
      else System.out.println("No");
      
   
   }
   
     if(filestrings[2].equals("spouse"))
   {
         
       ArrayList<String> XSpouse = new ArrayList<String>();
      for (Person p: hmap.get(filestrings[1]).getSpouse())
      {
      XSpouse.add(p.getName());
      }
      
      boolean compare = false;
    
      
    for (int i = 0; i < XSpouse.size(); i++)
    {
    
   if(filestrings[3].equals(XSpouse.get(i)))
         {
            compare = true;
         }
         
         }
         
   if(compare == true)
   {
   System.out.println("Yes");
   }
   
   if(compare == false)
   
   {
   System.out.println("No");
   }     
   
       
   }
   
     if(filestrings[2].equals("cousin"))
   {
   
   
   
   }
   
   if (filestrings[2].equals("ancestors"))
   {
   
    ArrayList<String> AllAncestors = new ArrayList<String>();
      for (Person p: hmap.get(filestrings[1]).Ancestors())
      {
      AllAncestors.add(p.getName());
      }
      
      boolean compare = false;
    
      
    for (int i = 0; i < AllAncestors.size(); i++)
    {
    
   if(filestrings[3].equals(AllAncestors.get(i)))
         {
            compare = true;
         }
         
         }
         
   if(compare == true)
   {
   System.out.println("Yes");
   }
   
   if(compare == false)
   
   {
   System.out.println("No");
   }
   
   
   
   }
   
   if(filestrings[2].equals("halfsibling"))
   {
   
    ArrayList<String> XHalfSiblings = new ArrayList<String>();
      for (Person p: hmap.get(filestrings[1]).halfSiblings())
      {
      XHalfSiblings.add(p.getName());
      }
      
      boolean compare = false;
    
      
    for (int i = 0; i < XHalfSiblings.size(); i++)
    {
    
   if(filestrings[3].equals(XHalfSiblings.get(i)))
         {
            compare = true;
         }
         
         }
         
   if(compare == true)
   {
   System.out.println("Yes");
   }
   
   if(compare == false)
   
   {
   System.out.println("No");
   }
   
}



}


//If the file says R first do this:
if(filestrings[0] .equals("R"))
{

   
   //Do sibling
   
   ArrayList<String> XSibling = new ArrayList<String>();
      for (Person p: hmap.get(filestrings[2]).getSiblings())
      {
      XSibling.add(p.getName());
      }
      
      boolean compare = false;
    
      
    for (int i = 0; i < XSibling.size(); i++)
    {
    
   if(filestrings[1].equals(XSibling.get(i)))
         {
            compare = true;
         }
         
         }
         
   if(compare == true)
   {
   System.out.println("sibling");
   }

   //Do Spouse relation
   
     ArrayList<String> XSpouse = new ArrayList<String>();
      for (Person p: hmap.get(filestrings[2]).getSpouse())
      {
      XSpouse.add(p.getName());
      }
      
      boolean Scompare = false;
    
      
    for (int i = 0; i < XSpouse.size(); i++)
    {
    
   if(filestrings[1].equals(XSpouse.get(i)))
         {
            Scompare = true;
         }
         
         }
         
   if(Scompare == true)
   {
   System.out.println("spouse");
   }
   
      

  //Do parent relation
   
    if(hmap.get(filestrings[2]).getParent1().getName().equals(filestrings[1]))
      {
      System.out.println("parent");
      }


   //Do Ancestors
   
    ArrayList<String> AllAncestors = new ArrayList<String>();
      for (Person p: hmap.get(filestrings[2]).Ancestors())
      {
      AllAncestors.add(p.getName());
      }
      
      boolean Acompare = false;
    
      
    for (int i = 0; i < AllAncestors.size(); i++)
    {
    
   if(filestrings[1].equals(AllAncestors.get(i)))
         {
            Acompare = true;
         }
         
         }
         
   if(Acompare == true)
   {
   System.out.println("ancestor");
   }
  
  
  
   
   //Do Cousin relation
   
   //Do Half siblings
       ArrayList<String> XHalfSiblings = new ArrayList<String>();
      for (Person p: hmap.get(filestrings[2]).halfSiblings())
      {
      XHalfSiblings.add(p.getName());
      }
      
      boolean HScompare = false;
    
      
    for (int i = 0; i < XHalfSiblings.size(); i++)
    {
    
   if(filestrings[1].equals(XHalfSiblings.get(i)))
         {
            HScompare = true;
         }
         
         }
         
   if(HScompare == true)
   {
   System.out.println("halfsibling");
   }


}


  
}



    }
   
   
   
   
   
   }
   
 
   
}

class Person{
 
 String name;
 ArrayList<Person> spouse;
 Person parent1;
 Person parent2;
 ArrayList<Person> children;
 
  public Person(String name)
  {
  
  this.name = name;
  this.spouse = new ArrayList<Person>();
  this.parent1 = parent1;
  this.parent2 = parent2;
  this.children = new ArrayList<Person>(); 
  
  }
  
 //Setters
 public void setName(String n)
 {
 this.name = n;
 }
 
 public void setSpouse(Person p)
 {
   if(!this.spouse.contains(p)){
   this.spouse.add(p);
   }
 }
 
  public void setParent1(Person p)
 {
   this.parent1 = p;
 }
 
  public void setParent2(Person p)
 {
   this.parent2 = p;
 }
 
  public void setChildren(Person p)
 {
  
   if(!this.children.contains(p)){
   this.children.add(p);
   }
 }
 
 
 //Getters
 public String getName()
 {
 return this.name;
 }
 
 public ArrayList<Person> getSpouse()
 {
 return this.spouse;
 }
 
 public Person getParent1()
 {
 return this.parent1;
 }
 
 public Person getParent2()
 {
 return this.parent2;
 }
 
 public ArrayList<Person> getChildren()
 {
 return this.children;
 }
 
 public ArrayList<Person> getSiblings()
 {
  
 ArrayList<Person> CommonSiblings = new ArrayList<Person>();
 CommonSiblings.addAll(this.parent1.getChildren());
 CommonSiblings.retainAll(this.parent2.getChildren());
 return CommonSiblings;
 
 }
 
 //For half siblings, get the unique children from each parent and add together into one list.
 public ArrayList<Person> halfSiblings(){
 
 ArrayList<Person> half1 = new ArrayList<Person>();
 ArrayList<Person> half2 = new ArrayList<Person>();
 ArrayList<Person> combined = new ArrayList<Person>();
 
 //Add all parents 1 and remove all of parent 2
 half1.addAll(this.parent1.getChildren());
 half2.removeAll(this.parent2.getChildren());
 
 //Add all parents 2 and remove all of parent 1
 half2.addAll(this.parent2.getChildren());
 half1.removeAll(this.parent1.getChildren());
 
 //Add all of the half siblings into one list
 combined.addAll(half1);
 combined.addAll(half2);
 
 return combined;
 
 }
 
 public ArrayList<Person> Ancestors() {
 
 LinkedList<Person> ans = new LinkedList();
 Person temp = null;
 ArrayList<Person> ancestors = new ArrayList<Person>();
 
 if(this.parent1 != null && this.parent1.parent1 != null){
 ans.add(this.parent1.parent1);
 ans.add(this.parent1.parent2);
 }
 
 
 if(this.parent2 != null && this.parent2.parent2 != null){
 ans.add(this.parent2.parent1);
 ans.add(this.parent2.parent2);
 }
 
 while(ans.size() > 0){
 

 temp = ans.remove();

   if(temp.parent1 != null && temp.parent2 != null)
   { 
 
 ans.add(temp.parent1);
 ans.add(temp.parent2);

 ancestors.add(parent1);
 ancestors.add(parent2);
 }
 
 ancestors.add(temp);
 }
 
 
 
 return ancestors;
 }
 
 }
 
