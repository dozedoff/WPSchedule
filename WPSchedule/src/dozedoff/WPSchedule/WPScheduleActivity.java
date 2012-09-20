/*  Copyright (C) 2012  Nicholas Wright
	
	part of 'WPSchedule', an Android wallpaper changer.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package dozedoff.WPSchedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * Main activity for WPSchedule
 */
public class WPScheduleActivity extends Activity {
	Button btnSchedule, btnGroup;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btnSchedule = (Button)this.findViewById(R.id.btnSchedule);
    	Button btnGroup = (Button)this.findViewById(R.id.btnGroup);
        
        btnSchedule.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent scheduleIntent = new Intent(v.getContext(), ScheduleActivity.class);
				startActivity(scheduleIntent);
			}
		});
        
        btnGroup.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent groupIntent = new Intent(v.getContext(), ImageGroupActivity.class);
				startActivity(groupIntent);
			}
		});
    }
}