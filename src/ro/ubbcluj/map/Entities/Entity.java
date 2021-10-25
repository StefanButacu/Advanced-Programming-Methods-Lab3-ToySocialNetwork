package ro.ubbcluj.map.Entities;

/**
 *
 * Generic class for business entity
 *
 * @param <ID>
 */
public class Entity<ID> {

    protected ID id;

    /**
     *
     * @param id - generic type
     */
    public Entity(ID id) {
        this.id = id;
    }

    /**
     * Getter id
     * @return id - of ID type
     */
    public ID getId() {
        return id;
    }

    /**
     *  set the new id for Entity object
     * @param id - ID
     */
    public void setId(ID id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;
        Entity<?> entity = (Entity<?>) o;
        return getId().equals(entity.getId());
    }


}
