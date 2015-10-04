(function() {
  'use strict';

  angular
    .module('tmsApp')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log, $rootScope) {
    $log.debug('runBlock end');
      
    $rootScope.$on('$viewContentLoaded', function () {
        console.log('$viewContentLoaded');
        $(document).foundation();
    });
      
  }

})();
