import { createContext } from "react";

export const DataContext = createContext();

export const DataProvider = ({ children }) => {

    const [ isLoading, setIsLoading ] = userState(true);

    const [ allUsers, setAllUsers ] = useState(null);
    const [ allEntries, setAllEntries ] = useState(null);
    const [ allShops, setAllShops ] = useState(null);
    const [ allAddRequests, setAllAddRequests ] = useState(null);

    const fetchUsers = async () => {

        const users = [];

        try {
            const response = await fetch('.../public/test-data/users.json');
            const data = await response.json();
            // console.log(data);

            data.forEach(user => {
                let user = ner User
            })
        } catch {

        } finally {

        }
    }

    useEffect(() => {
        fetchUsers();
    }, []);

    return <DataContext.Provider value={{}}>{ children }</DataContext.Provider>;
}