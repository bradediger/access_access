(defproject access_access "0.0.1-SNAPSHOT"
  :description "Suck data out of an Access database"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License - v 1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [clojess "0.3"]
                 [org.clojure/data.csv "0.1.2"]]
  :main ^:skip-aot access-access.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
