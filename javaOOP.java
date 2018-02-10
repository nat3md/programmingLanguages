public class Sequence extends Element
{
    protected Element element;
    protected Sequence next;
    
    public Sequence(){
        
        element = null;
        next = null;
    }

    public Sequence(Element element, Sequence next)
    {
        this.element=element;
        this.next=next;
    }

    // returns the current element
    public Element Get(){

        return element;
    }

    // Sets The element and sequence
    public void Set(Element val, Sequence n){

        element = val;
        next = n;
    }
    // Prints sequence
    public void Print()
    {
        // print sequence
        System.out.print("[ ");
        //print current element
        if(element != null)
        {
            element.Print();
        }
        
        //iterate to next location until null
        Sequence iterator = this.next;
        while(iterator != null)
        {
            if(iterator.element != null)
            {
                System.out.print(' ');
                iterator.element.Print();
                
            }
            iterator = iterator.next;
        }
        System.out.print(" ]");
    }
    // returns the content of the first element
    public Element first()
    {
        return element;
    }

    // returns the rest of the Sequence
    public Sequence rest()
    {
        return next;
    }
    
    // returns the length of the Sequence
    public int length()
    {
        int count = 0;
        Sequence elem=this;
        while(elem.next != null)
        {
            count++;
            elem = elem.next;
        }
        return count;
    }

    // adds element to the sequence

    public void add(Element elm, int pos)
    {
        Sequence temp = new Sequence();
        Sequence ptr= this;

        if(pos < 0 || pos > length())
        {
            System.out.println("Invalid Position.");
            System.exit(-1);
        }
        
        else
        {
            for(int i = 0;  i < pos; i++)
            {
                ptr= ptr.next;
            }
            
            temp.element = ptr.element;
            temp.next = ptr.next;
            ptr.element = elm;
            ptr.next = temp;
        };
        
    }
    
    public void delete(int pos)
    {
        Sequence temp = new Sequence();
        
        Sequence ptr= this;

        if(pos < 0 || pos > length())
        {
            System.out.println("Invalid Position.");
            System.exit(-1);
        }
        if(pos == 0)
        {
            element = this.next.element;
            this.next = this.next.next;
            return;
        }
        else
        {
            int i=1;
            temp=ptr.next;
            while( i < pos)
            {
                ptr=temp;
                temp = ptr.next;
                i++;
            }
            
           
            ptr.next = temp.next;
        };

      }
    
    // returns the element at a given index
    public Element index(int pos)
    {
        if (pos > this.length()|| pos <0)
        {
            System.out.println("Invalid Position");
        }
        Sequence iterator = this;
        for (int i=0; i<pos; i++)
        {
            iterator = iterator.next;
        }
        return iterator.element;
    }
    
    // takes out every subsequences within the sequence and make it into one sequence
    // with no subsequences.
    public Sequence flatten()
    {
        Sequence iterator=this;
        Sequence nuSeq= new Sequence();
        int counter= 0; 
        while(iterator != null)
        {
            if(iterator.element instanceof Sequence)
            {
                // typecasting to turn the Sequence type into an Elemeent type
                Sequence typeCast=(Sequence)iterator.element;
                typeCast= typeCast.flatten(); // recursive call for the nested subsequences
                

                while(typeCast != null)
                {
                    nuSeq.add(typeCast.element,counter);
                    typeCast=typeCast.next;
                    counter++;
                }
            }
            else
            {
                nuSeq.add(iterator.element,counter);
                counter++;
            }
            iterator=iterator.next;
        }
        return nuSeq;
    }
    
    public Sequence copy()
    {
        Sequence copySeq = new Sequence();
        Sequence iterator = this;
        int counter = 0;
        
        while(iterator.next != null)
        {
            if(iterator.element instanceof Sequence)
            {
                // typecasting to turn the Sequence type into an Elemeent type
                Sequence typeCast = ((Sequence)iterator.element).copy();// recursive call for the nested subsequences
                copySeq.add(typeCast,counter);
            }
            else
            {
                if (iterator.element != null)
                {
                    if(iterator.element instanceof MyInteger)
                    {
                        MyInteger copyInt = new MyInteger();
                        copyInt.Set( ((MyInteger)iterator.element).Get() );
                        copySeq.add(copyInt, counter);
                    }
                    else
                    {
                        MyChar copyChar = new MyChar();
                        copyChar.Set( ((MyChar)iterator.element).Get() );
                        copySeq.add(copyChar, counter);
                    }
                }
                else
                {
                    
                }
            }
            counter++;
            iterator = iterator.next;
        }
        return copySeq;
    }
    
    
    public SequenceIterator begin()
    {
        SequenceIterator begin = new SequenceIterator(this);
        return begin;
    }
    
    public SequenceIterator end()
    {
        Sequence iterator = this;
        Sequence specialSym = new Sequence(null,null);

        while(iterator.next != null)
        {
            iterator = iterator.next;
        }

        iterator.next=specialSym;
        SequenceIterator end= new SequenceIterator(iterator);
        
        return end;
    }

   


}

class SequenceIterator
{
    protected Sequence iterator;

    public SequenceIterator()
    {
        iterator = null;
    }
    
    public SequenceIterator(Sequence val)
    {
        iterator = val;
    }
    public boolean equal(SequenceIterator check)
    {
        if(this.iterator.element == check.iterator.element)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public SequenceIterator advance()
    {
       
        iterator = iterator.rest();
        return this;
    }
    
    public Element get()
    {
        return iterator.Get();
    }
    
    
}

class Pair extends Element {
    private MyChar key;
    private Element element;

    public Pair(){
        key = null;
        element = null;
    }

    public Pair(MyChar ch, Element elm){
        key = ch;
        element = elm;
    }

    public Element Get(){ return element; }
    
    public MyChar GetKey(){ return key; }

    public void Print(){
        System.out.print("(");
        key.Print();
        System.out.print(" ");
        element.Print();
        System.out.print(")");
    }

    public boolean is(MyChar val){
        if(key.equals(val)){
            return true;
        }
        else {
        return false;
        }
    }

    
    public void Set(Element elm){
        element = elm;
    }

    public boolean lesser(Pair obj){

        if(this.key == null){
            return false;
        } 
        
        if(obj.key.Get() >= this.key.Get() ){
            return true;
        } else {
            return false;
        }
    }
}
class Matrix extends Sequence
{
    private int rowsize; 
    private int colsize;
    
    public Matrix(int rowsize, int colsize)
    {
        this.rowsize = rowsize;
        this.colsize = colsize;

        for(int i = 0; i < rowsize * colsize; i++)
        {
            MyInteger integer= new MyInteger();
            this.add(integer, 0);
        }
        
    }
    
    public void Set(int row, int col, int value)
    {
        Sequence iteration = this;
        int index = (row * colsize) + col;
        for(int i = 0; i < index; i++)
        {
            iteration = iteration.rest();
        }
        MyInteger setter = (MyInteger)iteration.element;
        setter.Set(value);
 
    }
    
    public int Get(int row, int col)
    {
        Sequence iteration = this;
        int index = (row * colsize) + col;
        for(int i = 0; i < index; i++)
        {
            iteration = iteration.rest();
        }
        MyInteger getter = (MyInteger)iteration.element;
        return getter.Get();
    }
    
    public Matrix Sum(Matrix mat)
    {
        Matrix sumofMatrix= new Matrix (this.rowsize,this.colsize);
        if(this.rowsize != mat.rowsize || this.colsize != mat.colsize)
        {
            System.out.println("Dimensions do not match.");
            System.exit(-1);
        }
        for(int i=0; i<this.rowsize;i++)
        {
            for(int j=0; j<this.colsize; j++)
            {
                int total= this.Get(i,j)+ mat.Get(i,j);
                sumofMatrix.Set(i,j,total);
                total = 0;
            }
        }
        return sumofMatrix;
    }
    
    
    
    public Matrix Product(Matrix mat)
    {
       
        if(this.colsize!=mat.rowsize)
        {
            System.out.println("Dimensions are invalid");
            System.exit(-1);
        }

        Matrix multiplicationMatrix = new Matrix(rowsize, mat.colsize);
       
        for(int i = 0; i < rowsize; i++)
        {
            for(int j = 0; j < mat.colsize; j++)
            {
                int product =0;

                for(int n = 0; n < colsize; n++)
                {
                    int x = this.Get(i, n);
                    int y =  mat.Get(n, j);
                    product += (x * y);
                    
                }
                multiplicationMatrix.Set(i, j, product);
                
            }
        }
        
        return multiplicationMatrix;
    }
    

    public void Print()
    {
        
        for(int i = 0; i < rowsize; i++)
        {
            System.out.print("[ ");
            
            for(int j = 0; j < colsize; j++)
            {
                System.out.print(this.Get(i, j));
                System.out.print(" ");
            }
            System.out.println("]");
        }
    }
    
}

class MapIterator extends SequenceIterator {


    public MapIterator(){
        iterator=null;
    }

    public MapIterator(Sequence seq){
        iterator = seq;
    }

    public Pair get(){
        
        Pair getter= (Pair)iterator.Get();
        return getter;

    }

    
}


class Map extends Sequence {
    
    public Map(){
        super();
    }
    

    public void add(Pair inval){
        
        MapIterator iterator = new MapIterator(this);
        char key;
        int index = 0;
        int length= length();

        if(length == 0){
            add(inval, 0);
            return;
        }
        
        for(int i=0; i<length; i++)
        {
            key = iterator.get().GetKey().Get();

            if(iterator.get().lesser(inval) == false){
                break;
            }

            iterator.advance();
            index++;
        }

        super.add(inval, index);
    }

    public MapIterator find(MyChar key){
        MapIterator iterator = new MapIterator(this);
        int length= length();

        for(int i=0; i<length; i++){
            if( iterator.get().is(key) ){// should it be is? maybe change it to equal
                return iterator;
            }

            iterator.advance();
        }

        return end();
    }

        public MapIterator begin(){
        MapIterator begin= new MapIterator(this);
        return begin;
    }
    


    public MapIterator end()
    {
        Sequence iterator = this;
        MapIterator end = new MapIterator();
       

        while(iterator.next != null)
        {
            iterator = iterator.next;
        }

       
        MapIterator ending= new MapIterator(iterator);
        
        return ending;
    }



}
