package com.webwarp.api.teamworkpm.category;
//namespace TeamWorkPm\Category;

class Project extends com.webwarp.api.teamworkpm.Model
{

    protected  function _init()
    {
        list ($parent, $type) = explode('-', $this->_parent);
        $this->_parent = $parent;
        $this->_action = $type . 'Categories';
        $this->_fields = array(
            'name'=>true,
            'parent'=> false
        );
    }

    /**
     * Retrieve all Project Categories
     * GET /projectCategories.xml
     * Will return all project categories
     */
    public function getAll()
    {
        return $this->_get($this->_action);
    }
}