(ns access-access.core
  (require [clojess.core :as acc]
           [clojure.data.csv :as csv]
           [clojure.java.io :as io]
           [clojure.string :as s])
  (:gen-class))

(defn- sanitize [name]
  (s/replace name #"(?i)[^-_a-z1-9]+" "_"))

(defn- dump-table [db table-name]
  (when-let [table (try (acc/table db table-name)
                        (catch java.io.FileNotFoundException e
                            (printf "Missing linked table: %s\n" table-name)
                            nil))]
    (let [headers (map #(.getName %) (.getColumns table))
          data (map (fn [row] (map #(.get row %) headers))
                    (iterator-seq (.iterator table)))]
      (with-open [writer (io/writer (format "output/%s.csv" (sanitize table-name)))]
        (csv/write-csv writer [headers])
        (csv/write-csv writer data)))))

(defn -main ;; lein run path/to/file.accdb
  [& args]
  (if-let [filename (first args)]
    (with-open [db (acc/open-db filename)]
      (doseq [table (acc/table-names db)]
        (dump-table db table)))
    (prn "Please specify a filename as the argument!")))
