'use strict';
angular.module('routingModule', ['ngRoute','app'])

.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                .when('/', {
                    templateUrl: 'home.html'
                })
                .when('/search', {
                    templateUrl: 'searchview/searchview.html'
                })
                .when('/reserve', {
                    templateUrl: 'passengerview/passengerview.html'
                })
                .otherwise({
                    redirectTo: '/'
                });
    }])

.controller('testctrl',['$scope', function($scope){
   $scope.bla = 'lalala';     
}]);

