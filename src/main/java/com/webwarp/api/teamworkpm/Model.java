package com.webwarp.api.teamworkpm;
//namespace TeamWorkPm;

abstract public class Model extends com.webwarp.api.teamworkpm.rest.Model
{

    /*------------------------------
            PUBLIC METHOD
     ------------------------------*/

    public function get($id, $params = array())
    {
        $id = (int) $id;
        return $this->_get("$this->_action/$id", $params);
    }

    /**
     *
     * @param array $data
     * @return int
     */
    public function insert(array $data)
    {
        return $this->_post($this->_action, $data);
    }

    /**
     *
     * @param array $data
     * @return bool
     */
    public function update(array $data)
    {
        $id = (int) empty($data['id']) ? 0 : $data['id'];
        if ($id <= 0) {
            throw new Exception('Require field id');
        }
        return $this->_put("$this->_action/$id", $data);
    }
    /**
     *
     * @param array $data
     * @return bool
     */
    final public function save(array $data)
    {
        return empty($data['id']) ?
            $this->insert($data) :
            $this->update($data);
    }
    /**
     *
     * @param mixed $id
     * @return bool
     */
    public function delete($id)
    {
        $id = (int) $id;
        if ($id <= 0) {
            throw new Exception('Require param id');
        }
        return $this->_delete("$this->_action/$id");
    }
}