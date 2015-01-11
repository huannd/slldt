package com.dtcs.slldt.screen;

import com.dtcs.slldt.model.ResultModel;


/**
 * The listener interface for receiving ISync events.
 * The class that is interested in processing a ISync
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addISyncListener<code> method. When
 * the ISync event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ISyncEvent
 */
public interface ISyncListener {
	
	/**
	 * Sync start.
	 */
	public void syncStart();
	
	/**
	 * Sync complete.
	 *
	 * @param result the result
	 */
	public void syncComplete(ResultModel result);
}
