package com.webwarp.api.teamworkpm;
//namespace TeamWorkPm;

class Project extends Model
{
    protected function _init()
    {
        $this->_fields = array(
            // New Project Name
            'name'        => true,
            // [Optional. Project Description]
            'description' =>  false,
            // [Optional. Start date in yyyymmdd format]
            'start_date'  => array(
                'required'=> false,
                'attributes' => array(
                    'type'=>'integer'
                )
            ),
            // [Optional. End date in yyyymmdd format]
            'end_date'    => array(
                'required' => false,
                'attributes' => array(
                    'type'=>'integer'
                )
            ),
            // [Optional. Id of company to assign the project to]
            'company_id'  => array(
                'required' => false,
                'attributes' => array(
                    'type' => 'integer'
                )
            ),
            // [Optional. Name of a new company to assign the project to]
            'new_company'    => false,
            //[Optional. Numeric ID of project category, 0 = no category]
            'category_id'     => false,
            /*
            'announcement'   => false,
            'show_announcement' => array(
                'required' => false,
                'attributes' => array(
                    'type'=>'boolean'
                )
            ),*/
            'notifyeveryone' => array(
                'required' => false,
                'attributes' => array(
                    'type'=>'boolean'
                )
            ),
            'status'         => false
        );
    }

    /**
     * Retrieves all accessible projects; including active/inactive and archived projects.
     * You can optionally append a date to the call to return only those projects recently updated.
     * This is very useful if you are implementing local caching as you won't have to recheck
     * everything therefore making your applicaton much faster. You can pass in a date and/or a date
     * with a time using the variables updatedAfterDate and updatedAfterTime.
     * @return TeamWorkPm\Response\Model
     */
    public function getAll($updatedAfterDate = null, $updatedAfterTime = null)
    {
        return $this->_getByStatus('all', $updatedAfterDate, $updatedAfterTime);

    }

    /**
     *
     * @param type $date
     * @param type $time
     * @return TeamWorkPm\Response\Model
     */
    public function getActive($updatedAfterDate = null, $updatedAfterTime = null)
    {
        return $this->_getByStatus('active', $updatedAfterDate, $updatedAfterTime);
    }

    /**
     *
     * @param type $date
     * @param type $time
     * @return TeamWorkPm\Response\Model
     */
    public function getArchived($updatedAfterDate = null, $updatedAfterTime = null)
    {
        return $this->_getByStatus('archived', $updatedAfterDate, $updatedAfterTime);
    }

    /**
     *
     * @param type $status
     * @param type $date
     * @param type $time
     * @return type
     */
    private function _getByStatus($status, $date, $time)
    {
        $params = array();
        if ($date !== null) {
            $params['updatedAfterDate'] = $date;
            if ($time !== null) {
                $params['updatedAfterTime'] = $time;
            }
        }
        $params['status'] = $status;
        return $this->_get("$this->_action", $params);
    }

    /**
     * Surprisingly, this will retrieve all of your projects, which have been starred!
     * @return array|SimpleXMLElement
     */
    public function getStarred()
    {
        return $this->_get("$this->_action/starred");
    }

    /**
     * Adds a project to your list of favourite projects.
     * @param int $id
     * @return bool
     */
    public function star($id)
    {
        $id = (int) $id;
        if (empty($id)) {
            throw new \TeamWorkPm\Exception('Required field id');
        }
        return $this->_put("$this->_action/$id/star");
    }

    /**
     * Removes a project from your list of favourite projects.
     * @param int $id
     * @return bool
     */
    public function unStar($id)
    {
        $id = (int) $id;
        if (empty($id)) {
            throw new \TeamWorkPm\Exception('Required field id');
        }
        return $this->_put("$this->_action/$id/unstar");
    }

    /**
     * Shortcut for active project
     *
     * @param type $id
     * @return bool
     */
    public function activate($id)
    {
        $data = array();
        $data['id'] = $id;
        $data['status'] = 'active';
        return $this->update($data);
    }

    /**
     * Shortcut for archive project
     *
     * @param type $id
     * @return bool
     */
    public function archive($id)
    {
        $data = array();
        $data['id'] = $id;
        $data['status'] = 'archived';
        return $this->update($data);
    }
}