# NJDEngine


### Version
0.0.2b

### How to use

 
**Creating a window:**
```Java
 Display display = new Display(1024,720);
 
 // [displayoptions] //
 
 display.createDisplay();
 
 display.start
```

 
**Adding something to Render**
```Java
display.add(new TestClass);



//* TestClass *//

public class TestClass implements Renderable{

    public void render(Graphics g){
        TextRenderer.text("Some Text", 20, 20, 10, Color.RED);
    }

}
```

**Adding something to update**
```java
display.addTicker(new TestClass);



//* TestClass *//

public class TestClass implements Tickable{

    public void tick(){
       //do something
    }

}
```


### Display options

```Java
Display display = new Display(DisplaySize.FULLSIZE); // Creates a full sized window

void setBorder(boolean); //Borderless window

void setSyncToFrames(int); //sets the framelimit

void setVsync(boolean); //turns on/off vSync

```
