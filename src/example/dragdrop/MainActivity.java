package example.dragdrop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View.DragShadowBuilder;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;


@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnTouchListener,
		OnDragListener {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.facebook).setOnTouchListener(this);
		findViewById(R.id.youtube).setOnTouchListener(this);
		findViewById(R.id.linkedin).setOnTouchListener(this);
		findViewById(R.id.top_box).setOnDragListener(this);
		findViewById(R.id.bottom_container).setOnDragListener(this);
		


	}

	@Override
	public boolean onTouch(View v, MotionEvent e) {
		// TODO Auto-generated method stub
		if (e.getAction() == MotionEvent.ACTION_DOWN) {
			DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
			v.startDrag(null, shadowBuilder, v, 0);
			v.setVisibility(View.INVISIBLE);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean onDrag(View v, DragEvent e) {
		// TODO Auto-generated method stub

		switch (e.getAction()) {
		case DragEvent.ACTION_DROP:
			View view = (View) e.getLocalState();
			ViewGroup from = (ViewGroup) view.getParent();
			from.removeView(view);
			LinearLayout to = (LinearLayout) v;
			to.addView(view);
			view.setVisibility(View.VISIBLE);

			break;

          //the drag point has entered the bounding box of the View 
     case DragEvent.ACTION_DRAG_ENTERED: 
    	 v.setBackground(getResources().getDrawable(R.drawable.targetshape));
            break; 

          //the user has moved the drag shadow outside the bounding box of the View 
      case DragEvent.ACTION_DRAG_EXITED: 
    	  v.setBackground(getResources().getDrawable(R.drawable.normalshape));
           break; 
  
            // the drag and drop operation has concluded.
		case DragEvent.ACTION_DRAG_ENDED:
			v.setBackground(getResources().getDrawable(R.drawable.normalshape));
			break;
                  
	default:
				
				
			break;
		}

		return true;
	}
}
