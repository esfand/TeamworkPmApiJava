package com.webwarp.api.teamworkpm.example;


require './bootstrap.php';

test_bootstrap(function($command){
    if (in_array($command, array('update', 'get', 'delete'))) {
        return get_first_file_category();
    } elseif (in_array($command, array('insert', 'get_by_project'))) {
        return get_first_project();
    }
});

function test_insert($project_id) {
    $category = TeamWorkPm::factory('Category/File');
    try {
        echo '------------------TEST INSERT---------------------', "\n";
        $data = array(
          'project_id'=> $project_id,
          'name'=>'File category . ' . rand(1, 10)
        );
        // SERIA BUENO SI EL API DEVOLVIERA EL ID DEL PROJECTO
        $id = $category->insert($data);
        echo 'Insert File category: ', $id, "\n", "\n";
    } catch (Exception $e) {
        print_r($e);
    }
}

function test_update($id) {
    $category = TeamWorkPm::factory('Category/File');
    try {
        echo '------------------TEST UPDATE---------------------', "\n";
        $data = array(
          'id'=>$id,
          'name'=>'File category ' . rand(1, 10)
        );
        // SERIA BUENO SI EL API DEVOLVIERA EL ID DEL PROJECTO
        if ($category->update($data)) {
            echo 'Update File category', "\n", "\n";
        }
    } catch (Exception $e) {
        print_r($e);
    }
}


function test_get($id) {
    $category = TeamWorkPm::factory('Category/File');
    try {
        echo '------------------TEST GET---------------------', "\n";
        $c = $category->get($id);
        print_r($c);
    } catch (Exception $e) {
        print_r($e);
    }
}


function test_get_by_project($project_id) {
    $category = TeamWorkPm::factory('Category/File');
    try {
        echo '------------------TEST GET BY PROJECT---------------------', "\n";
        $categories = $category->getByProject($project_id);
        foreach($categories as $c) {
            print_r($c);
        }
    } catch (Exception $e) {
        print_r($e);
    }
}

function test_delete($id) {
    $category = TeamWorkPm::factory('Category/File');
    try {
        echo '------------------TEST DELETE---------------------', "\n";
        if ($category->delete($id)) {
            echo 'Delete File category: ', $id, "\n";
        }
    } catch (Exception $e) {
        print_r($e);
    }
}