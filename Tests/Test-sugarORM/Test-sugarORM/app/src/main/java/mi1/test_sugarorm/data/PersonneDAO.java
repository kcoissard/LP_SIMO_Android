package mi1.test_sugarorm.data;

import java.util.List;

/**
 * Created by fbm on 18/10/16.
 */

public class PersonneDAO {

    public static List<Personne> selectAll() {
        return Personne.listAll(Personne.class);
    }
}
