import { createContext, useEffect } from "react";
import { User, CoffeeShop, Entry } from '../classes/Exports';

export const DataContext = createContext();

export const DataProvider = ({ children }) => {

    const [ isLoading, setIsLoading ] = useState(true);

    const [ allUsers, setAllUsers ] = useState(null);
    const [ allEntries, setAllEntries ] = useState(null);
    const [ allShops, setAllShops ] = useState(null);
    const [ allAddRequests, setAllAddRequests ] = useState(null);

    const fetchEntries = async () => {
        console.log("starting fetch");

        const entries = [];

        try {
            const response = await fetch('http://localhost:8080/api/entries');

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || `ERROR - Status ${response.status}`);
            } else {

                const data = await response.json();

                data.forEach(entry => {
                    let user = new User(entry.user.id, entry.user.userName, entry.user.userEmail, entry.user.Password, entry.user.userCity);
                    let coffeeShop = new CoffeeShop(entry.coffeeShop.id, entry.CoffeeShop.shopName, entry.CoffeeShop.shopAddress, entry.CoffeeShop.shopPhone, entry.CoffeeShop.shopHours);
                    let newEntry = new Entry(entry.id, coffeeShop, entry.drinkOrder, entry.rating, entry.review, entry.wouldRecommend, entry.visitDate, user);
                    entries.push[newEntry];
                });

                setAllEntries(entries);
            }

        } catch(error) {

        } finally {

        }
    }

    useEffect(() => {
        fetchEntries();
    }, []);

    useEffect(() => {
        if (allEntries !== null) {
           setIsLoading(false); 
        }
    }, [allEntries]);

    return <DataContext.Provider value={{isLoading, allEntries, fetchEntries}}>{ children }</DataContext.Provider>;
}
