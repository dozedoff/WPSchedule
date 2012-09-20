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

public class ImageGroupActivity extends Activity {
	Button createGroup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.group);
		
		createGroup =  (Button)this.findViewById(R.id.group);
		createGroup.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent creatGroupIntent = new Intent(getApplicationContext(), CreateImageGroupActivity.class);
				startActivity(creatGroupIntent);
			}
		});
	}
}
