package blocks;

import static org.lwjgl.opengl.GL11.*;
import java.util.ArrayList;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;

@SuppressWarnings("unused")

public class Blocks {
	
	public ArrayList<Block> newBlock(blockType type, int x, int y, ArrayList<Block> blockArray){
		x -= x % 20;
		y -= y % 20;
		Block block = new Block(type,x,y);
		blockArray.add(block);
		return(blockArray);
	}
	
	public Blocks(){
		
		try{
			
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("Block Game");
			Display.create();
			
		}
		
		catch (LWJGLException e){
			
			e.printStackTrace();
			
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		ArrayList<Block> blocks = new ArrayList<Block>();
		double mouseX,mouseY;
		boolean leftMouseDown,leftMouseAlreadyDown = false;
		
		blocks = newBlock(blockType.DIRT,2,0,blocks);
		blocks = newBlock(blockType.STONE,32,0,blocks);
		blocks = newBlock(blockType.METAL,3,22,blocks);
		
		while (!Display.isCloseRequested()){
			
			mouseX = Mouse.getX();
			mouseY = 480 - Mouse.getY();
			leftMouseDown = Mouse.isButtonDown(1);
			
			if(!leftMouseDown && leftMouseAlreadyDown){
				leftMouseAlreadyDown = false;
			}
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			for(int i = 0; i < blocks.size(); i++){
				switch(blocks.get(i).type){
				case DIRT:
					glColor3d(0.85,0.53,0.1);
					break;
				case METAL:
					glColor3d(0.65,0.49,0.24);
					break;
				case STONE:
					glColor3d(0.752941,0.752941,0.752941);
					break;
				default:
					glColor3d(1,0,1);
					break;
				}
				glRectd(blocks.get(i).x,blocks.get(i).y,blocks.get(i).x + 20,blocks.get(i).y + 20);
			}
			
			Display.update();
			Display.sync(60);
			
			if(leftMouseDown && !leftMouseAlreadyDown){
				leftMouseAlreadyDown = true;
			}
			
		}
		
		Display.destroy();
		
	}

	public static void main(String[] args) {
		
		new Blocks();

	}

}
