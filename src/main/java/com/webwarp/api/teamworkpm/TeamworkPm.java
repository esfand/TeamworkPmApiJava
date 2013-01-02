package com.webwarp.api.teamworkpm;

/**
 *
 * @package    TeamWorkPm
 * Copyright   @ Loduis Madariaga
 * @license    LICENCE.txt
 * @version    0.0.1-dev
 */

final class TeamWorkPm
{

    private static $_COMPANY = NULL;

    private static $_API_KEY = NULL;

    private function  __construct()
    {

    }
    /**
     *
     * @param string $class
     * @return TeamWorkPm\Model
     */
    public static function factory($class_name)
    {
        $class_name = str_replace('/', '\\', $class_name);
        $class_name = ucfirst(
                        preg_replace(
                            '/(\\\.)/e',
                            'strtoupper(\'$1\');',
                            $class_name
                        )
                      );
        if (strcasecmp($class_name, 'task\\list') === 0) {
            $class_name = 'task_list';
        }
        $class_name = '\\' . __CLASS__ . '\\' .  $class_name;
        return forward_static_call_array(
              array($class_name, 'getInstance'),
              array(self::$_COMPANY, self::$_API_KEY)
        );
    }

    public static function setAuth($company, $key)
    {
        self::$_COMPANY = $company;
        self::$_API_KEY = $key;
    }

    public static function setFormat($value)
    {
        \TeamWorkPm\Rest::setFormat($value);
    }

    private function __clone()
    {

    }

    /**
     * TeamWorkPm PSR-0 autoloader
     */
    public static function autoload($className)
    {
        $thisClass = str_replace(__NAMESPACE__ . '\\', '', __CLASS__);
        $baseDir = __DIR__;
        $length = strlen($thisClass);

        $className = ltrim($className, '\\');

        if (substr($baseDir, - $length) === $thisClass) {
            $baseDir = substr($baseDir, 0, - $length);
        } elseif (substr($className, 0, $length + 1) === $thisClass . '\\') {
            $className = substr($className, $length + 1);
        }
        $fileName  = $baseDir;

        if ($lastNsPos = strripos($className, '\\')) {
            $namespace = substr($className, 0, $lastNsPos);
            $className = substr($className, $lastNsPos + 1);
            if ($namespace && substr($namespace, 0, 1) !== '\\') {
                $namespace = '\\' . $namespace;
            }
            $fileName  .= str_replace('\\', DIRECTORY_SEPARATOR, $namespace) .
                                                            DIRECTORY_SEPARATOR;
        } else {
            $className = DIRECTORY_SEPARATOR . $className;
        }
        $fileName .= str_replace('_', DIRECTORY_SEPARATOR, $className) . '.php';
        if (file_exists($fileName)) {
            require $fileName;
        }
    }

    /**
     * Register TeamWorkPm's PSR-0 autoloader
     */
    public static function registerAutoloader()
    {
        spl_autoload_register(__CLASS__ . '::autoload');
    }
}

TeamWorkPm::registerAutoloader();