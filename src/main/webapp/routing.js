'use strict';
angular.module('routingModule', ['ngRoute','resultModule'])

.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                .when('/', {
                    templateUrl: 'home.html'
                })
                .when('/search', {
                    templateUrl: 'searchview/searchview.html',
                    controller: 'searchCntr'
                })
                .when('/reserve', {
                    templateUrl: 'passengerview/passengerview.html',
                    controller: 'searchCntr'
                })
                .otherwise({
                    redirectTo: '/'
                });
    }]);



