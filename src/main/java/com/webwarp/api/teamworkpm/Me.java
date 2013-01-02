package com.webwarp.api.teamworkpm;
//namespace TeamWorkPm;

class Me extends com.webwarp.api.teamworkpm.rest.Model
{

    public function get()
    {
        return $this->_get("me");
    }
}