package edu.cesi.libgdx.frogger.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ValueComparator 
{
	public Object[] sortValueMap(Map<String, Integer> map)
	{
	  final List<Entry<String, Integer>> entries = new ArrayList<Entry<String, Integer>>(map.entrySet());
	 
	   Collections.sort(entries, new Comparator<Entry<String, Integer>>() 
	  {
		    public int compare(final Entry<String, Integer> e1, final Entry<String, Integer> e2) 
		    {
		      return e2.getValue().compareTo(e1.getValue());
		    }
	  });
	   return convertListStringArray(entries);
	}
	
	
	private Object[] convertListStringArray(List<Entry<String, Integer>> entries)
	{	
		Map<String,Integer> map = new HashMap<String,Integer>();
		Object[] tmp = entries.toArray();
		return tmp;
	}
	
}
