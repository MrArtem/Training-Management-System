(function() {
  'use strict';

  angular
    .module('tmsApp')
    .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('mycourses', {
        url: '/',
        templateUrl: 'app/page.mycourses/mycourses.tmpl.html',
        controller: 'MyCoursesController',
        controllerAs: 'mycourses'
    }).state('browse', {
        url: '/browse',
        templateUrl: 'app/page.browse/browse.tmpl.html',
        controller: 'BrowseController',
        controllerAs: 'browse'
    }).state('createcourse', {
        url: '/createcourse',
        templateUrl: 'app/page.createcourse/createcourse.tmpl.html',
        controller: 'CreateCourseController',
        controllerAs: 'createcourse'
    }).state('courseinfo', {
        url: '/courseinfo/:courseId',
        templateUrl: 'app/page.courseinfo/courseinfo.tmpl.html',
        controller: 'CourseInfoController',
        controllerAs: 'courseinfo'
    });

    $urlRouterProvider.otherwise('/');
  }

})();
