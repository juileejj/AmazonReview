/**
 * Created by hadoop on 4/25/17.
 */
angular.module('MyApp', [])
    .controller('SearchController', ['$scope', '$http', function ($scope, $http) {
        $scope.user = {};
        $scope.results = [];

        $scope.search = function () {
            /* the $http service allows you to make arbitrary ajax requests.
             * in this case you might also consider using angular-resource and setting up a
             * User $resource. */
            $http.get('/your/url/search', { params: user },
                function (response) { $scope.results = response; },
                function (failure) { console.log("failed :(", failure); });
        }
    }]);