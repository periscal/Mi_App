package GUI;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public interface GUI{
		public abstract ArrayList<HashMap<Field,Object>> getAtributos(String id);
}
